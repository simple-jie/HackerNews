package com.simple_jie.hackernews.screen.main;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.simple_jie.domain.entities.Category;
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
        loadData();
    }

    @Override
    public void loadData() {
        view.showLoading();
        useCase.setCategory(category);
        useCase.execute(new DefaultSubscriber<List<NewsItem>>() {
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showEmpty();
            }

            @Override
            public void onNext(List<NewsItem> newsItems) {
                super.onNext(newsItems);
                view.renderData(newsItems);
            }
        });
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
