package com.simple_jie.hackernews.utility;

import com.simple_jie.domain.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

@Singleton
public class UIPostExecutionThread implements PostExecutionThread {

    @Inject
    public UIPostExecutionThread() {}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
