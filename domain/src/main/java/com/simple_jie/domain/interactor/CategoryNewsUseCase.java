package com.simple_jie.domain.interactor;

import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.CategoryNews;
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
public class CategoryNewsUseCase extends UseCase<CategoryNewsUseCase.Args, CategoryNews> {


    NewsRepository repository;

    public static class Args {
        boolean forceRefresh;
        Category category = Category.topstories;

        public Args(boolean forceRefresh, Category category) {
            this.category = category;
            this.forceRefresh = forceRefresh;
        }
    }

    @Inject
    public CategoryNewsUseCase(NewsRepository repository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }


    @Override
    protected Observable<CategoryNews> buildUseCaseObservable(Args args) {
        return repository.getNews(args.category, args.forceRefresh);
    }
}
