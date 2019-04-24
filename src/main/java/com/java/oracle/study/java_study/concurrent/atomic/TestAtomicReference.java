package com.java.oracle.study.java_study.concurrent.atomic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ToString
public class TestAtomicReference {

//    private static AtomicReference<Integer> atomicReference = new AtomicReference(1);

    private static TestAtomicReference testAtomicReference1 = new TestAtomicReference();
    private static AtomicReference<TestAtomicReference> atomicReferenceAtomicReference = new AtomicReference<>(testAtomicReference1);

    @Getter
    @Setter
    private int count = 0;

    public static void main(String[] args) {
        TestAtomicReference testAtomicReference = new TestAtomicReference();
        testAtomicReference.setCount(1);
        atomicReferenceAtomicReference.compareAndSet(testAtomicReference1, testAtomicReference);

        log.info("count:{}", atomicReferenceAtomicReference.get());
    }
}
