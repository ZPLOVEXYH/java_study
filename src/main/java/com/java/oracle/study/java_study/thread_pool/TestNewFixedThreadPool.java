package com.java.oracle.study.java_study.thread_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 固定线程数
 */
@Slf4j
public class TestNewFixedThreadPool {

    public static void main(String[] args) {
        /**
         * 方法一
         */
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                log.info("executors newFixedThreadPool");
//            }
//        });
//
//        log.info("this mian");
//        executorService.shutdown();

        /**
         * 方法二
         */
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L ,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                log.info("executors newFixedThreadPool");
            }
        });

        log.info("this main");
        executorService.shutdown();
    }
}
