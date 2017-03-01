package com.simple_jie.hackernews.di.modules;

import com.simple_jie.domain.entities.NewsItem;
import com.simple_jie.hackernews.di.PerActivity;
import com.simple_jie.hackernews.screen.BaseActivity;
import com.simple_jie.hackernews.screen.ScreenLifecycle;
import com.simple_jie.hackernews.screen.main.CategoryNewsPresenter;
import com.simple_jie.hackernews.screen.main.CategoryNewsContract;
import com.simple_jie.hackernews.screen.main.NewsListAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */
@Module
public class ActivityModule {
    private BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    BaseActivity providerActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    ScreenLifecycle.Provider providerScreenLifecycleProvider() {
        return activity.getLifecycleProvider();
    }

    @Provides
    @PerActivity
    CategoryNewsContract.IPresenter providerCategoryNewsPresenter(CategoryNewsPresenter presenter) {
        return presenter;
    }

//    @PerActivity
//    @Provides
//    NewsListAdapter providerNewsListAdapter(NewsListAdapter adapter) {
//        return adapter;
//    }
}
