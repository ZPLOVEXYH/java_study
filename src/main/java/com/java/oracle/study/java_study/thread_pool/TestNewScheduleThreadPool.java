package com.java.oracle.study.java_study.thread_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestNewScheduleThreadPool {

    public static void main(String[] args) {
        /**
         * 方法一
         */
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                log.info("executors newScheduledThreadPool");
//            }
//        }, 1, 3, TimeUnit.SECONDS);
//
//        log.info("this main");

//        scheduledExecutorService.shutdown();

        /**
         * 方法二
         */
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                log.info("time task");
            }
        }, 1, 4 * 1000);

        log.info("this main");
    }
}
