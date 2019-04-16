package com.java.oracle.study.java_study.concurrent.cas;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 一个同步辅助类，在完成一组正在其他线程中执行的操作 之前，它允许一个或多个线程一直等待。
 * 闭锁可以延迟线程的进度直到其到达终止状态，闭锁可以用来确保某些活 动直到其他活动都完成才继续执行:
 * 确保某个计算在其需要的所有资源都被初始化之后才继续执行;
 * 确保某个服务在其依赖的所有其他服务都已经启动之后才启动;
 * 等待直到某个操作所有参与者都准备就绪再继续执行。
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(5);
        TestRunnable tr = new TestRunnable(cdl);
        Instant instant = Instant.now();
        for (int i = 0; i < 5; i++) {
            new Thread(tr).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long between = ChronoUnit.MILLIS.between(instant, Instant.now());

        System.out.println("耗时 = " + between);
    }
}

class TestRunnable implements Runnable {

    private CountDownLatch cdl;

    public TestRunnable(CountDownLatch countDownLatch) {
        this.cdl = countDownLatch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5000; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } finally {
            cdl.countDown();
        }
    }
}



