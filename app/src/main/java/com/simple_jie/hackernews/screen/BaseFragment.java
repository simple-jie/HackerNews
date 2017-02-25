package com.simple_jie.hackernews.screen;

import android.support.v4.app.Fragment;

import com.simple_jie.hackernews.di.HasComponent;

/**
 * Created by Xingbo.Jie on 24/2/17.
 */

public class BaseFragment extends Fragment {
    public BaseFragment() {
        super();
    }

    public <Component> Component getComponent(Class<Component> componentClass) {
        return componentClass.cast(((HasComponent) getActivity()).getComponent());
    }
}
