package com.simple_jie.hackernews.screen.main;

import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.interactor.CategoryNewsUseCase;
import com.simple_jie.hackernews.di.PerActivity;

import javax.inject.Inject;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */
@PerActivity
public class CategoryNewsPresenter implements CategoryNewsContract.IPresenter {

    Category category;
    CategoryNewsContract.IView view;
    CategoryNewsUseCase useCase;

    @Inject
    public CategoryNewsPresenter(CategoryNewsUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void setCategroy(Category categroy) {
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void showDetail() {

    }

    @Override
    public void setView(CategoryNewsContract.IView view) {

    }

    @Override
    public void start() {

    }
}
