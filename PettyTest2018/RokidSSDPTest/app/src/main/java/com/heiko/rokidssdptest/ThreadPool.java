package com.heiko.rokidssdptest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 *
 * @author EthanCo
 * @since 2018/3/13
 */

public class ThreadPool {
    private ThreadPool() {
        executorPool = Executors.newCachedThreadPool();
    }

    private static class SingletonHolder {
        private static ThreadPool sInstance = new ThreadPool();
    }

    public static ThreadPool getInstance() {
        return SingletonHolder.sInstance;
    }

    private final ExecutorService executorPool;

    public ExecutorService getExecutorPool() {
        return executorPool;
    }
}
