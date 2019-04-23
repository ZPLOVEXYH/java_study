package com.java.oracle.study.java_study.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class TestAtomicReference {

    private static AtomicReference<Integer> atomicReference = new AtomicReference(1);

    public static void main(String[] args) {
        atomicReference.compareAndSet(1, 2);
        atomicReference.compareAndSet(1, 3);
        atomicReference.compareAndSet(2, 6);
        atomicReference.compareAndSet(3, 7);

        log.info("count:{}", atomicReference.get());
    }
}
