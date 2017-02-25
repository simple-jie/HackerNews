package com.simple_jie.hackernews.screen;

import android.support.annotation.UiThread;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Xingbo.Jie on 25/12/16.
 */

public class ScreenLifecycleProxy implements ScreenLifecycle.Provider, ScreenLifecycle.Listener {

    private Set<ScreenLifecycle.Listener> listeners = new HashSet<>();

    @Override
    @UiThread
    public void registerLifeCycleListener(ScreenLifecycle.Listener iLifeCycle) {
        if (iLifeCycle != null)
            listeners.add(iLifeCycle);
    }

    @Override
    @UiThread
    public void removeLifeCycleListener(ScreenLifecycle.Listener iLifeCycle) {
        if (iLifeCycle != null)
            listeners.remove(iLifeCycle);
    }

    @Override
    @UiThread
    public void clear() {
        listeners.clear();
    }


    @Override
    public void onCreate() {
        Iterator<ScreenLifecycle.Listener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onCreate();
        }
    }

    @Override
    public void onStart() {
        Iterator<ScreenLifecycle.Listener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onStart();
        }
    }

    @Override
    public void onResume() {
        Iterator<ScreenLifecycle.Listener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onStart();
        }
    }

    @Override
    public void onPause() {
        Iterator<ScreenLifecycle.Listener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onPause();
        }
    }

    @Override
    public void onStop() {
        Iterator<ScreenLifecycle.Listener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onStop();
        }
    }

    @Override
    public void onDestroy() {
        Iterator<ScreenLifecycle.Listener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDestroy();
        }
    }
}
