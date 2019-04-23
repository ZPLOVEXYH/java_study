package com.java.oracle.study.java_study.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.LongAdder;

@Slf4j
public class TestLongAddr {

    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                incre();
                log.info("long:{}", longAdder);
            }).start();
        }
    }

    private static void incre(){
        longAdder.increment();
    }
}
