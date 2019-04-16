package com.java.oracle.study.java_study.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    public static void main(String[] args) {
        LockDemo ld = new LockDemo();
        for (int i = 0; i < 3; i++) {
            new Thread(ld, i + 1 + "号窗口").start();
        }
    }
}

class LockDemo implements Runnable {

    private int ticket = 100;

//    @Override
//    public void run() {
//        while (true){
//            synchronized (LockDemo.class){
//                if(0 < ticket){
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println(Thread.currentThread().getName() + " 完成售票，余票为：" + --ticket);
//                }
//            }
//        }
//    }

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (0 < ticket) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 完成售票，余票为：" + --ticket);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
