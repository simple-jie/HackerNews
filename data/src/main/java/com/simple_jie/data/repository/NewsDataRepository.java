package com.simple_jie.data.repository;

import com.simple_jie.data.cache.NewsCache;
import com.simple_jie.data.entity.ItemEntity;
import com.simple_jie.data.entity.mapper.EntityMapper;
import com.simple_jie.data.net.APIService;
import com.simple_jie.data.net.RetrofitManager;
import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.Item;
import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.domain.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public class NewsDataRepository implements NewsRepository {

    RetrofitManager retrofitManager;
    APIService apiService;
    NewsCache itemCache;

    @Inject
    public NewsDataRepository(RetrofitManager retrofitManager, NewsCache itemCache) {
        this.retrofitManager = retrofitManager;
        apiService = retrofitManager.getApiService(APIService.class);
        this.itemCache = itemCache;
    }

    @Override
    public Observable<List<NewsItem>> getNews(Category category) {
        return apiService.news(category.value)
                .flatMap(new Func1<List<Integer>, Observable<List<NewsItem>>>() {
                    @Override
                    public Observable<List<NewsItem>> call(List<Integer> integers) {
                        return Observable.from(integers)
                                .map(new Func1<Integer, NewsItem>() {
                                    @Override
                                    public NewsItem call(Integer integer) {
                                        return new NewsItem(integer);
                                    }
                                })
                                .toList();
                    }
                });
    }

    @Override
    public Observable<Item> getNewItem(int id, boolean forceRefresh) {
        Func1<ItemEntity, Item> mapper = new Func1<ItemEntity, Item>() {
            @Override
            public Item call(ItemEntity itemEntity) {
                return EntityMapper.convert(itemEntity);
            }
        };

        Observable<Item> net = apiService
                .item(id)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<ItemEntity>() {
                    @Override
                    public void call(ItemEntity itemEntity) {
                        itemCache.insertOrUpdate(itemEntity);
                    }
                })
                .map(mapper);

        if (forceRefresh) {
            return net;
        }

        Observable<Item> cache = itemCache
                .loadItem(id)
                .map(mapper);


        return Observable
                .concat(cache, net)
                .first(new Func1<Item, Boolean>() {
                    @Override
                    public Boolean call(Item item) {
                        return item != null;
                    }
                });
    }

}
