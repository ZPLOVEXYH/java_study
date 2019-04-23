package com.java.oracle.study.java_study.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicIntegerTest {

    public static void main(String[] args) {
        Thread[] threads = new Thread[100];
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int i = 0 ; i < threads.length ; i++) {
            threads[i] = new Thread(atomicDemo);
            threads[i].start();
        }
    }

}

@Slf4j
class AtomicDemo implements Runnable {

    private AtomicInteger serialNumber  = new AtomicInteger();

    @Override
    public void run() {
//        try {
//            Thread.sleep(200);
            log.info("count:{}", add());
//        } catch (InterruptedException e) {
//            log.error("exception:{}", e);
//        }
    }

    private int add(){
        return serialNumber.getAndIncrement();
    }
}
