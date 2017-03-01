package com.simple_jie.domain.repository;

import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.CategoryNews;
import com.simple_jie.domain.entities.Item;

import rx.Observable;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public interface NewsRepository {

    Observable<CategoryNews> getNews(Category category, boolean forceRefresh);

    Observable<Item> getNewItem(int id, boolean forceRefresh);
}
