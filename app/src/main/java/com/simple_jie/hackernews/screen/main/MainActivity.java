package com.simple_jie.hackernews.screen.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.simple_jie.domain.entities.Category;
import com.simple_jie.domain.entities.Item;
import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.domain.interactor.CategoryNewsUseCase;
import com.simple_jie.domain.interactor.DefaultSubscriber;
import com.simple_jie.domain.interactor.NewsItemUseCase;
import com.simple_jie.hackernews.R;
import com.simple_jie.hackernews.di.HasComponent;
import com.simple_jie.hackernews.di.components.ActivityComponent;
import com.simple_jie.hackernews.di.modules.ActivityModule;
import com.simple_jie.hackernews.screen.BaseActivity;
import com.simple_jie.hackernews.utility.TimeUtil;

import java.sql.Time;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent>, RefreshDateChangeListener{

    ActivityComponent component;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    long refreshTime = 0;

    //for test
//    @Inject
//    CategoryNewsUseCase newsUseCase;
//    @Inject
//    NewsItemUseCase itemUseCase;
    //for test end

    @Override
    protected void onCreateA(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (refreshTime != 0) {
            toolbar.setSubtitle(TimeUtil.getRefreshTime(refreshTime));
        }
        //for test
//        getComponent().inject(this);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                newsUseCase.execute(new DefaultSubscriber<List<NewsItem>>() {
//                            @Override
//                            public void onNext(List<NewsItem> newsItems) {
//                                super.onNext(newsItems);
//
//                                if (newsItems.size() > 0) {
//                                    itemUseCase.execute(new DefaultSubscriber<Item>() {
//                                        @Override
//                                        public void onNext(Item item) {
//                                            super.onNext(item);
//                                        }
//                                    }, newsItems.get(0).getId(), true);
//                                }
//                            }
//                        }
//                , new CategoryNewsUseCase.Args());
//            }
//        });

        //for test end.
    }

    @Override
    public ActivityComponent getComponent() {
        if (component == null) {
            component = getApplicationComponent().plus(new ActivityModule(this));
        }
        return component;
    }

    @Override
    public void onDateChange(long time) {
        refreshTime = time;
        if (toolbar != null) {
            toolbar.setSubtitle(TimeUtil.getRefreshTime(time));
        }
    }
}
