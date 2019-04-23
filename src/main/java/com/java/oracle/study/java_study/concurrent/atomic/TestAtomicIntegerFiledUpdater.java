package com.java.oracle.study.java_study.concurrent.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
public class TestAtomicIntegerFiledUpdater {

    private static AtomicIntegerFieldUpdater<TestAtomicIntegerFiledUpdater> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(TestAtomicIntegerFiledUpdater.class, "count");

    @Getter
    private volatile int count = 100;

    public static void main(String[] args) {
        TestAtomicIntegerFiledUpdater atomicIntegerFiledUpdater = new TestAtomicIntegerFiledUpdater();

        // 只执行一遍
        if(atomicIntegerFieldUpdater.compareAndSet(atomicIntegerFiledUpdater, 100, 200)) {
            log.info("update success 1, {}", atomicIntegerFiledUpdater.getCount());
        }

        if(atomicIntegerFieldUpdater.compareAndSet(atomicIntegerFiledUpdater, 100, 200)) {
            log.info("update success 2, {}", atomicIntegerFiledUpdater.getCount());
        } else {
            log.info("update failed 3, {}", atomicIntegerFiledUpdater.getCount());
        }


    }

}
