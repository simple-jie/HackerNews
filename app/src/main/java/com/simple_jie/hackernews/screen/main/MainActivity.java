package com.simple_jie.hackernews.screen.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.simple_jie.hackernews.R;
import com.simple_jie.hackernews.di.HasComponent;
import com.simple_jie.hackernews.di.components.ActivityComponent;
import com.simple_jie.hackernews.di.modules.ActivityModule;
import com.simple_jie.hackernews.screen.BaseActivity;

public class MainActivity extends BaseActivity implements HasComponent<ActivityComponent> {

    ActivityComponent component;

    @Override
    protected void onCreateA(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public ActivityComponent getComponent() {
        if (component == null) {
            component = getApplicationComponent().plus(new ActivityModule(this));
        }
        return component;
    }
}
