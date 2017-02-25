package com.simple_jie.hackernews.di.components;

import com.simple_jie.hackernews.di.PerActivity;
import com.simple_jie.hackernews.di.modules.ActivityModule;
import com.simple_jie.hackernews.screen.main.CategoryNewsFragment;
import com.simple_jie.hackernews.screen.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(CategoryNewsFragment fragment);
}
