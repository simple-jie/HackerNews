package com.simple_jie.hackernews.screen.main;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.hackernews.R;
import com.simple_jie.hackernews.di.components.ActivityComponent;
import com.simple_jie.hackernews.screen.BaseFragment;
import com.simple_jie.hackernews.widget.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public class CategoryNewsFragment extends BaseFragment implements CategoryNewsContract.IView {

    @Inject
    CategoryNewsContract.IPresenter presenter;

    @Inject
    NewsListAdapter newsAdapter;

    Category category;
    View rootView;
    Unbinder unBinder;


    @BindView(R.id.news_list)
    RecyclerView newsList;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(ActivityComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.content_main, container, false);
            unBinder = ButterKnife.bind(this, rootView);
        } else {
            ViewParent parent = rootView.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(rootView);
            }
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        newsList.setLayoutManager(layoutManager);
        newsList.setAdapter(newsAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL,
                new ColorDrawable(getContext().getResources().getColor(R.color.mountain_mist))
        );
        dividerItemDecoration.setHeight(1);
        newsList.addItemDecoration(dividerItemDecoration);

        presenter.setView(this);
        if (savedInstanceState == null) {
            presenter.loadData();
        }

        newsAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NewsItem item) {
                presenter.showDetail(item);
            }

            @Override
            public void onOpenUrlClick(String url) {
                presenter.openUrl(url);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshData();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unBinder.unbind();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void renderData(List<NewsItem> data) {
        newsAdapter.setData(data);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEmpty() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(CategoryNewsContract.IPresenter presenter) {

    }
}
