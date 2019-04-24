package com.java.oracle.study.java_study.concurrent.synchronize;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TestSynchronizedClass {

    // 修饰的是静态方法
    private static synchronized void method01(int j){
        for(int i = 0; i < 1000; i++){
            log.info("test1：{} - {}", j, i);
        }
    }

    // 修饰的是整个类对象
    private void method02(int j){
        synchronized (TestSynchronizedMethod.class) {
            for(int i = 0; i < 1000; i++){
                log.info("test2：{} - {}", j, i);
            }
        }
    }

    public static void main(String[] args) {
        TestSynchronizedClass testSynchronizedClass = new TestSynchronizedClass();
        TestSynchronizedClass testSynchronizedClass2 = new TestSynchronizedClass();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            method01(1);
        });

        executorService.execute(() -> {
            testSynchronizedClass2.method02(2);
        });
    }

}
