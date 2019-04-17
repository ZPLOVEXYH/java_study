package com.java.oracle.study.java_study.thread_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 单线程池执行器
 */
@Slf4j
public class TestNewSingleThreadExecutor {

    public static void main(String[] args) {
        /**
         * 方法一：使用Executors来创建线程池
         */
//        // 创建单个线程的线程池
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        // 线程池中的线程执行任务
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                log.info("this runnable do something");
//            }
//        });
//        log.info("this main do something");
//        // 关闭线程池
//        executorService.shutdown();

        /**
         * 方法二：使用ThreadPoolExecutor
         */
        // 创建单个线程的线程池
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
        // 线程池中的线程执行任务
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                log.info("this runnable do something");
            }
        });
//        int activeThread = ((ThreadPoolExecutor) executorService).getActiveCount();
//        System.out.println(activeThread);
        log.info("this main do something");
        // 关闭线程池
        executorService.shutdown();
    }
}
