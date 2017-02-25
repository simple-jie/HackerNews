package com.simple_jie.hackernews.utility;

import com.simple_jie.domain.executor.ThreadExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

@Singleton
public class JobThreadExecutor implements ThreadExecutor {

    private ThreadPoolExecutor threadPoolExecutor;
    private final static int CORE_POOL_SIZE = 3;
    private final static int MAXIMUM_POOL_SIZE = 5;
    private final static int KEEP_ALIVE_TIME = 10;
    private final static TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private final BlockingQueue<Runnable> workQueue;
    private final ThreadFactory threadFactory;

    @Inject
    public JobThreadExecutor() {
        workQueue = new LinkedBlockingDeque<>();
        threadFactory = new JobThreadFactory();

        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                KEEP_ALIVE_TIME_UNIT,
                workQueue,
                threadFactory);
    }

    @Override
    public void execute(Runnable command) {
        if (command != null)
            threadPoolExecutor.execute(command);
    }

    static class JobThreadFactory implements ThreadFactory {
        private static int id = 1;
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "JobThreadExecutor - " + (id++));
        }
    }
}
