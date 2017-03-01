package com.simple_jie.domain.interactor;

import com.simple_jie.domain.entities.Item;
import com.simple_jie.domain.executor.PostExecutionThread;
import com.simple_jie.domain.executor.ThreadExecutor;
import com.simple_jie.domain.repository.NewsRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public class NewsItemUseCase extends UseCase<NewsItemUseCase.Args, Item> {
    NewsRepository repository;

    public static class Args {
        int id;
        boolean forceRefresh;

        public Args(int id, boolean forceRefresh) {
            this.id = id;
            this.forceRefresh = forceRefresh;
        }
    }

    @Inject
    public NewsItemUseCase(NewsRepository newsRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = newsRepository;
    }


    @Override
    protected Observable<Item> buildUseCaseObservable(Args args) {
        return repository.getNewItem(args.id, args.forceRefresh);
    }
}
