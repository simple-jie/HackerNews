package com.simple_jie.domain.repository;

import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.Item;
import com.simple_jie.domain.entities.NewsItem;

import java.util.List;

import rx.Observable;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public interface NewsRepository {

    Observable<List<NewsItem>> getNews(Category category);

    Observable<Item> getNewItem(int id, boolean forceRefresh);
}
