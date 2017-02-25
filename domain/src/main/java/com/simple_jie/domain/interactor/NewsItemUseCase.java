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

public class NewsItemUseCase extends UseCase<Item> {
    NewsRepository repository;
    int id;
    boolean forceRefresh;

    @Inject
    public NewsItemUseCase(NewsRepository newsRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = newsRepository;
    }


    @Override
    public void execute(DefaultSubscriber useCaseSubscriber) {
        throw new IllegalArgumentException("id must be set");
    }

    public void execute(DefaultSubscriber useCaseSubscriber, int id, boolean forceRefresh) {
        this.forceRefresh = forceRefresh;
        this.id = id;
        super.execute(useCaseSubscriber);
    }

    @Override
    protected Observable<Item> buildUseCaseObservable() {
        return repository.getNewItem(id);
    }
}
