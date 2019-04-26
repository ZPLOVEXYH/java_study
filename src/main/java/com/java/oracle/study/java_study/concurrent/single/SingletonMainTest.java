package com.java.oracle.study.java_study.concurrent.single;

import lombok.extern.slf4j.Slf4j;

/**
 * 如何实现一个线程安全的单例
 */
@Slf4j
public class SingletonMainTest {

    public static void main(String[] args) {
        // 饿汉模式1
        SingletonTest00 singletonTest00 = SingletonTest00.getInstance();
        log.info("对象1：{}", singletonTest00);

        // 饿汉模式2
        SingletonTest01 singletonTest01 = SingletonTest01.getInstance();
        log.info("对象2：{}", singletonTest01);

        // 懒汉模式
        SingletonTest02 singletonTest02 = SingletonTest02.getInstance();
        log.info("对象3：{}", singletonTest02);

        // 静态内部类
        SingletonTest03 singletonTest03 = SingletonTest03.getInstance();
        log.info("对象4：{}", singletonTest03);
    }
}
