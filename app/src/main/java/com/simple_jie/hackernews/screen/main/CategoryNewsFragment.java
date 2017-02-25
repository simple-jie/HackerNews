package com.simple_jie.hackernews.screen.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.hackernews.R;
import com.simple_jie.hackernews.di.components.ActivityComponent;
import com.simple_jie.hackernews.screen.BaseFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public class CategoryNewsFragment extends BaseFragment implements CategoryNewsContract.IView {

    @Inject
    CategoryNewsContract.IPresenter presenter;

    Category category;
    View rootView;

    public static CategoryNewsFragment newInstance(Category category) {
        CategoryNewsFragment fragment = new CategoryNewsFragment();
        Bundle args = new Bundle();
        args.putSerializable("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    public CategoryNewsFragment() {
        super();
        if (getArguments() != null) {
            this.category = (Category) getArguments().get("category");
        }

        if (this.category == null) {
            this.category = Category.topstories;
        }

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            getComponent(ActivityComponent.class).inject(this);
            rootView = inflater.inflate(R.layout.content_main, container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);

        if (savedInstanceState != null) {
            presenter.loadData();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void renderData(List<NewsItem> data) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void setPresenter(CategoryNewsContract.IPresenter presenter) {

    }
}
