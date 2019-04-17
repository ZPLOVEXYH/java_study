package com.java.oracle.study.java_study.thread_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 创建缓存线程池
 */
@Slf4j
public class TestNewCachedThreadPool {

    public static void main(String[] args) {
        /**
         * 方法一
         */
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("this runnable");
//            }
//        });
//
//        log.info("test main");
//        executorService.shutdown();

        /**
         * 方法二
         */
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                log.info("this runnable");
            }
        });

        log.info("this main");

        executorService.shutdown();

    }
}
