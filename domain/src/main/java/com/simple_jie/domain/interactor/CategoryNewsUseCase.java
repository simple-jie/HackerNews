package com.simple_jie.domain.interactor;

import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.domain.executor.PostExecutionThread;
import com.simple_jie.domain.executor.ThreadExecutor;
import com.simple_jie.domain.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */
public class CategoryNewsUseCase extends UseCase<List<NewsItem>> {

    Category category = Category.topstories;
    NewsRepository repository;

    @Inject
    public CategoryNewsUseCase(NewsRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    public CategoryNewsUseCase setCategory(Category category) {
        this.category = category;
        return this;
    }

    @Override
    protected Observable<List<NewsItem>> buildUseCaseObservable() {
        return repository.getNews(category);
    }
}
