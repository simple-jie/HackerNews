package com.simple_jie.hackernews.screen.main;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.CategoryNews;
import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.domain.interactor.CategoryNewsUseCase;
import com.simple_jie.domain.interactor.DefaultSubscriber;
import com.simple_jie.hackernews.di.PerActivity;
import com.simple_jie.hackernews.screen.BaseActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */
@PerActivity
public class CategoryNewsPresenter implements CategoryNewsContract.IPresenter {

    Category category = Category.topstories;
    CategoryNewsContract.IView view;
    CategoryNewsUseCase useCase;

    BaseActivity activity;

    @Inject
    public CategoryNewsPresenter(BaseActivity activity, CategoryNewsUseCase useCase) {
        this.useCase = useCase;
        this.activity = activity;
    }

    @Override
    public void setCategroy(Category categroy) {
        this.category = categroy;
    }

    @Override
    public void refreshData() {
        loadData(true);
    }

    @Override
    public void loadData() {
        loadData(false);
    }

    private void loadData(boolean foreRefresh) {
        view.showLoading();
        useCase.execute(new DefaultSubscriber<CategoryNews>() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showEmpty();
            }

            @Override
            public void onNext(CategoryNews categoryNews) {
                super.onNext(categoryNews);
                view.renderData(categoryNews);
            }
        }, new CategoryNewsUseCase.Args(foreRefresh, category));
    }

    @Override
    public void showDetail() {

    }

    @Override
    public void openUrl(String url) {
        if (TextUtils.isEmpty(url))
            return;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }

    @Override
    public void showDetail(NewsItem item) {

    }

    @Override
    public void setView(CategoryNewsContract.IView view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
