package com.ethanco.simpleframe.frame.utils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description  线程池工具类
 * Created by chenqiao on 2015/10/19.
 */
public class ThreadPoolUtil {

    public enum Type {
        SingleThread,
        FixedThread,
        CachedThread,
        ScheduledSingleThread
    }

    private static final int DEFAULT_THREADS = 3;

    private ExecutorService executorService = null;

    private Type nowType;

    public ThreadPoolUtil(Type type) {
        nowType = type;
        switch (type) {
            case SingleThread:
                executorService = Executors.newSingleThreadExecutor();
                break;
            case FixedThread:
                executorService = Executors.newFixedThreadPool(DEFAULT_THREADS);
                break;
            case CachedThread:
                executorService = Executors.newCachedThreadPool();
                break;
            case ScheduledSingleThread:
                executorService = Executors.newSingleThreadScheduledExecutor();
                break;
        }
    }

    /**
     * 延迟一定时间后开始执行Runnable,效果同{@link new Handler().postDelay}
     *
     * @param runnable     执行的任务
     * @param initialDelay 延迟时间
     */
    public void schedule(Runnable runnable, long initialDelay) {
        ((ScheduledExecutorService) executorService).schedule(runnable, initialDelay, TimeUnit.MILLISECONDS);
    }

    /**
     * 延迟一定时间后，开始执行Runnable，完成后延迟一定时间再重新执行Runnable
     *
     * @param runnable     执行的任务
     * @param initialDelay 初始延迟时间
     * @param delay        上次完成后至下次开始延迟的时间
     */
    public void scheduleWithFixedDelay(Runnable runnable, long initialDelay, long delay) {
        ((ScheduledExecutorService) executorService).scheduleWithFixedDelay(runnable, initialDelay, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 每隔固定时间开始执行Runnable，固定频率
     *
     * @param runnable     执行的任务
     * @param initialDelay 初始延迟时间
     * @param period       每隔period时间再次执行Runnable任务
     */
    public void scheduleWithFixedRate(Runnable runnable, long initialDelay, long period) {
        ((ScheduledExecutorService) executorService).scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    public void execute(List<Runnable> runnables) {
        for (Runnable runnable : runnables) {
            executorService.execute(runnable);
        }
    }

    public Future<?> submit(Runnable runnable) {
        return executorService.submit(runnable);
    }

    public boolean isShutDown() {
        return executorService.isShutdown();
    }

    public void shutDown() {
        executorService.shutdown();
    }

    public void shutDownNow() {
        executorService.shutdownNow();
        executorService = null;
    }

}