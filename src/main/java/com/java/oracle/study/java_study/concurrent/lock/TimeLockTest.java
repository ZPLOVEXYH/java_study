package com.java.oracle.study.java_study.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLockTest {

    public static void main(String[] args) {
        TimeLockDemo timeLockDemo = new TimeLockDemo();
        new Thread(timeLockDemo, "线程1").start();
        new Thread(timeLockDemo, "线程2").start();
    }
}

class TimeLockDemo implements Runnable {

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 获得锁！");
                // 休眠10秒钟
                Thread.sleep(4000);
            } else {
                System.out.println(Thread.currentThread().getName() + " 尝试了5秒钟去获取锁，未获取得到锁！");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");
        } finally {
            // 判断是否是当前的线程，如果是的话就释放当前线程的锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
