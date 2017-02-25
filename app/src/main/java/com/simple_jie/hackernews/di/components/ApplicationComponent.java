package com.simple_jie.hackernews.di.components;

import com.simple_jie.hackernews.di.modules.ActivityModule;
import com.simple_jie.hackernews.di.modules.ApplicationModule;
import com.simple_jie.hackernews.di.modules.RepositoryModule;
import com.simple_jie.hackernews.screen.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity activity);

    ActivityComponent plus(ActivityModule module);
}
