package com.simple_jie.hackernews;

import android.app.Application;

import com.simple_jie.hackernews.di.components.ApplicationComponent;
import com.simple_jie.hackernews.di.components.DaggerApplicationComponent;
import com.simple_jie.hackernews.di.modules.ApplicationModule;

/**
 * Created by Xingbo.Jie on 24/2/17.
 */

public class NewsApplication extends Application {

    private static NewsApplication _ins;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        _ins = this;
        initAppApplicationComponent();


    }

    private void initAppApplicationComponent() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return _ins.applicationComponent;
    }

    public static NewsApplication getInstance() {
        return _ins;
    }
}
