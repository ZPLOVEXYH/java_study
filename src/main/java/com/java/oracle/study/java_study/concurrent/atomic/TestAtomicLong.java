package com.java.oracle.study.java_study.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class TestAtomicLong {

    private static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            new Thread(() -> {
                long a = add();
                log.info("long：{}", a);
            }).start();
        }
    }

    private static long add(){
        return atomicLong.incrementAndGet();
    }
}
