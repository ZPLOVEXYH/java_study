package com.java.oracle.study.java_study.concurrent.synchronize;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * synchronized作用于调用的对象，而不作用于所有的对象
 * 包括：修饰的同步方法块和修饰的同步方法里面的语句块
 */
@Slf4j
public class TestSynchronizedMethod {

    // 同步语句块
    private void method01(int j){
        synchronized (this){
            for(int i = 0; i < 100; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }

        for(int i = 0; i < 100; i++) {
            log.warn("warn {} - {}", j, i);
        }
    }

    // 同步方法块
    private synchronized void method02(int j){
        for(int i = 0; i < 1000; i++){
            log.info("test2 {} - {}", j, i);
        }

        log.warn("warn:{}", j);
    }

    public static void main(String[] args) {
        TestSynchronizedMethod testSynchronizedMethod = new TestSynchronizedMethod();
//        TestSynchronizedMethod testSynchronizedMethod2 = new TestSynchronizedMethod();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            testSynchronizedMethod.method01(1);
        });

        executorService.execute(() -> {
            testSynchronizedMethod.method01(2);
        });
    }
}
