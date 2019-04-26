package com.java.oracle.study.java_study.concurrent.single;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现单例模式方法（使用lock关键字）
 */
@Slf4j
public class SingletonTest04 {

    private static Lock lock = new ReentrantLock();

    // 定义构造方法为私有的
    private SingletonTest04() {}

    private static SingletonTest04 singletonTest04 = null;

    public static SingletonTest04 getInstance(){
        if(lock.tryLock()) {
            try {
                if(null == singletonTest04) {
                    singletonTest04 = new SingletonTest04();
                }
            } finally {
                lock.unlock();
            }
        }

        return singletonTest04;
    }
}
