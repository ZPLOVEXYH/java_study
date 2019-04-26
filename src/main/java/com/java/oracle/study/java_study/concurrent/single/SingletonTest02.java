package com.java.oracle.study.java_study.concurrent.single;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现单例模式方法（懒汉模式）
 */
@Slf4j
public class SingletonTest02 {

    // 定义构造方法为私有的
    private SingletonTest02() {}

    private static SingletonTest02 singletonTest02 = null;

    public static SingletonTest02 getInstance(){
        return new SingletonTest02();
    }
}
