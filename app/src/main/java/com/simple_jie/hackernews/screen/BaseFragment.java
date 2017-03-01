package com.simple_jie.hackernews.screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.simple_jie.hackernews.di.HasComponent;

/**
 * Created by Xingbo.Jie on 24/2/17.
 */

public class BaseFragment extends Fragment {

    protected final ScreenLifecycleProxy lifecycleProxy;

    public BaseFragment() {
        super();
        lifecycleProxy = new ScreenLifecycleProxy();
    }

    public <Component> Component getComponent(Class<Component> componentClass) {
        return componentClass.cast(((HasComponent) getActivity()).getComponent());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleProxy.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycleProxy.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycleProxy.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleProxy.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        lifecycleProxy.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycleProxy.onDestroy();
    }
}
