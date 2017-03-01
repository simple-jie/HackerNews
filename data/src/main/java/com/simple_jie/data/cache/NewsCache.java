package com.simple_jie.data.cache;

import com.simple_jie.data.entity.CategoryNewsEntity;
import com.simple_jie.data.entity.ItemEntity;
import com.simple_jie.domain.entities.Category;

import rx.Observable;

/**
 * Created by Xingbo.Jie on 1/3/17.
 */

public interface NewsCache {
    void insertOrUpdate(ItemEntity itemEntity);

    Observable<ItemEntity> loadItem(int id);

    void insertOrUpdate(CategoryNewsEntity categoryNews);

    Observable<CategoryNewsEntity> loadNews(Category category);
}
