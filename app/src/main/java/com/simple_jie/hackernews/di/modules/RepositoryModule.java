package com.simple_jie.hackernews.di.modules;

import com.simple_jie.data.cache.NewsCache;
import com.simple_jie.data.cache.NewsItemCacheImpl;
import com.simple_jie.data.repository.NewsDataRepository;
import com.simple_jie.domain.repository.NewsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    NewsRepository provideNewsRepository(NewsDataRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    NewsCache provideNewsItemCache(NewsItemCacheImpl newsItemCache) {
        return newsItemCache;
    }
}
