package com.simple_jie.hackernews.screen;

/**
 * Created by Xingbo.Jie on 25/12/16.
 */

public interface ScreenLifecycle {
    interface Provider {
        void registerLifeCycleListener(ScreenLifecycle.Listener iLifeCycle);
        void removeLifeCycleListener(ScreenLifecycle.Listener iLifeCycle);
        void clear();
    }

    interface Listener {
        void onCreate();
        void onStart();
        void onResume();
        void onPause();
        void onStop();
        void onDestroy();
    }
}
