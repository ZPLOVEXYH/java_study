package com.java.oracle.study.java_study.concurrent.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockFairTest {

    public static void main(String[] args) {
        LockFairTest lockFairTest = new LockFairTest();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(lockFairTest, "线程" + i);

            threadList.add(thread);
        }
        threadList.forEach(s -> log.info(s.getName()));

        for (Thread t : threadList) {
            t.start();
        }
    }

}

@Slf4j
class LockFairTest implements Runnable {

    // 重入锁，设置公平锁
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    @Override
    public void run() {
        reentrantLock.lock();
        log.info("{}，获取得到锁", Thread.currentThread().getName());
        reentrantLock.unlock();
    }
}
