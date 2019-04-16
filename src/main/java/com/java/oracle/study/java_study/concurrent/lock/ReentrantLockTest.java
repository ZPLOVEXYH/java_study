package com.java.oracle.study.java_study.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        Thread t = new Thread(reentrantLockDemo, "线程A");
        t.start();
        Thread t1 = new Thread(reentrantLockDemo, "线程B");
        t1.start();
        t1.interrupt();
    }
}

class ReentrantLockDemo implements Runnable {
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            lock.lockInterruptibly();

            System.out.println(Thread.currentThread().getName() + " running");
            // 休眠5秒
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " finished");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");
        } finally {
            lock.unlock();
        }
    }
}

