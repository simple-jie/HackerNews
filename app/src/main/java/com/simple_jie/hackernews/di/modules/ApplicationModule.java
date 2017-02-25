package com.simple_jie.hackernews.di.modules;

import android.app.Application;
import android.content.Context;

import com.simple_jie.domain.executor.PostExecutionThread;
import com.simple_jie.domain.executor.ThreadExecutor;
import com.simple_jie.hackernews.utility.JobThreadExecutor;
import com.simple_jie.hackernews.utility.UIPostExecutionThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */
@Module
public class ApplicationModule {
    private final Application application;
    public ApplicationModule(Application app) {
        this.application = app;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    public ThreadExecutor provideThreadExecutor(JobThreadExecutor threadExecutor) {
        return threadExecutor;
    }

    @Provides
    @Singleton
    public PostExecutionThread providePostExecutionThread(UIPostExecutionThread thread) {
        return thread;
    }
}
