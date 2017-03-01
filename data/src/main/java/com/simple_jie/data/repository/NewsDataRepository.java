package com.simple_jie.data.repository;

import com.simple_jie.data.cache.NewsCache;
import com.simple_jie.data.entity.CategoryNewsEntity;
import com.simple_jie.data.entity.ItemEntity;
import com.simple_jie.data.entity.mapper.EntityMapper;
import com.simple_jie.data.net.APIService;
import com.simple_jie.data.net.RetrofitManager;
import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.CategoryNews;
import com.simple_jie.domain.entities.Item;
import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.domain.repository.NewsRepository;

import java.util.ArrayList;
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
    NewsCache newsCache;

    @Inject
    public NewsDataRepository(RetrofitManager retrofitManager, NewsCache itemCache) {
        this.retrofitManager = retrofitManager;
        apiService = retrofitManager.getApiService(APIService.class);
        this.newsCache = itemCache;
    }

    @Override
    public Observable<CategoryNews> getNews(final Category category, boolean forceRefresh) {
        final Observable<CategoryNews> net = apiService.news(category.value)
                .doOnNext(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        if (integers != null) {
                            newsCache.insertOrUpdate(new CategoryNewsEntity(null, category, integers, System.currentTimeMillis() / 1000));
                        }
                    }
                })
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
                })
                .map(new Func1<List<NewsItem>, CategoryNews>() {
                    @Override
                    public CategoryNews call(List<NewsItem> newsItems) {
                        return CategoryNews.newBuilder()
                                .category(category)
                                .refreshTime(System.currentTimeMillis() / 1000)
                                .items(newsItems)
                                .build();
                    }
                });

        if (forceRefresh)
            return net;

        Observable<CategoryNews> cache = newsCache
                .loadNews(category)
                .map(new Func1<CategoryNewsEntity, CategoryNews>() {
                    @Override
                    public CategoryNews call(CategoryNewsEntity categoryNews) {
                        if (categoryNews == null || categoryNews.getIds() == null || categoryNews.getIds().size() == 0)
                            return null;

                        List<NewsItem> result = new ArrayList<>();
                        for (Integer integer : categoryNews.getIds()) {
                            result.add(new NewsItem(integer));
                        }

                        return CategoryNews.newBuilder()
                                .items(result)
                                .refreshTime(categoryNews.getRefeshTime())
                                .category(category)
                                .build();
                    }
                });

        return Observable
                .concat(cache, net)
                .first(new Func1<CategoryNews, Boolean>() {
                    @Override
                    public Boolean call(CategoryNews categoryNews) {
                        return categoryNews != null;
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
                        newsCache.insertOrUpdate(itemEntity);
                    }
                })
                .map(mapper);

        if (forceRefresh) {
            return net;
        }

        Observable<Item> cache = newsCache
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
