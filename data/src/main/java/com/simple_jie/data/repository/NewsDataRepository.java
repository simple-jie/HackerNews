package com.simple_jie.data.repository;

import com.simple_jie.data.net.APIService;
import com.simple_jie.data.net.RetrofitManager;
import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.Item;
import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.domain.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public class NewsDataRepository implements NewsRepository {

    RetrofitManager retrofitManager;
    APIService apiService;

    @Inject
    public NewsDataRepository(RetrofitManager retrofitManager) {
        this.retrofitManager = retrofitManager;
        apiService = retrofitManager.getApiService(APIService.class);
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
    public Observable<Item> getNewItem(int id) {
        return apiService.item(id);
    }

}
