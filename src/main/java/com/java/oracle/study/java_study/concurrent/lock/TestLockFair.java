package com.java.oracle.study.java_study.concurrent.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockFair {

    public static void main(String args[]) throws InterruptedException {
        LockFairDemo ld = new LockFairDemo();

        List<Thread> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    ld.print();
                }
            }, "线程" + i);
            list.add(thread);
        }

        for (Thread t : list) {
            try {
                System.out.println("线程名称:" + t.getName());
                t.start();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class LockFairDemo {

    private ReentrantLock lock = new ReentrantLock(true);

    public void print() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
}