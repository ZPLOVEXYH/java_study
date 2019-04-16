package com.java.oracle.study.java_study.concurrent;

public class TestAtomicDemo {

    public static void main(String args[]) {
        Thread[] threads = new Thread[10];
        AtomicDemo a = new AtomicDemo();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(a);
            threads[i].start();
        }
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
