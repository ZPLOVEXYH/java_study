package com.java.oracle.study.java_study.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子变量：jdk1.5 后java.util.concurrent.atomic 包下提供了常用的原子变量：
 * 1. volatile 保证内存的可见性
 * 2. CAS (Compare -And - Swap) 算法保证数据的原子性
 * CAS 算法是硬件对于并发操作共享数据的支持
 * CAS 包括三个操作数：
 * 内存值 V
 * 预估值 A
 * 更新值 B
 * 当且仅当V==A 时，操作V=B，否则不做任何操作
 */
public class TestAtomicDemo {

    public static void main(String args[]) {
        Thread[] threads = new Thread[10];
        AtomicTest a = new AtomicTest();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(a);
            threads[i].start();
        }
    }
}

class AtomicTest implements Runnable {

    AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
            System.out.println(getSerialNum());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getSerialNum() {
        return atomicInteger.getAndIncrement();
    }
}

class AtomicDemo implements Runnable {

    private volatile int serialNumber = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
            System.out.println(getSerialNumber());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getSerialNumber() {
        return serialNumber++;
    }
}
