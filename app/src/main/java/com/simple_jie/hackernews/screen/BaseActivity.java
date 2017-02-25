package com.simple_jie.hackernews.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simple_jie.hackernews.NewsApplication;
import com.simple_jie.hackernews.di.components.ApplicationComponent;
import com.simple_jie.hackernews.utility.Naviagtor;

import javax.inject.Inject;

/**
 * Created by Xingbo.Jie on 24/2/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ScreenLifecycleProxy lifecycleProxy;

    @Inject
    protected Naviagtor naviagtor;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleProxy = new ScreenLifecycleProxy();
        getApplicationComponent().inject(this);
        onCreateA(savedInstanceState);

        lifecycleProxy.onCreate();
    }

    protected abstract void onCreateA(Bundle savedInstanceState);

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleProxy.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycleProxy.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycleProxy.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycleProxy.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycleProxy.onDestroy();
    }

    public ScreenLifecycle.Provider getLifecycleProvider() {
        return lifecycleProxy;
    }

    public ApplicationComponent getApplicationComponent() {
        return NewsApplication.getApplicationComponent();
    }
}
