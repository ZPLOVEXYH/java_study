package com.java.oracle.study.java_study.concurrent.single;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现单例模式方法（饿汉模式1）
 */
@Slf4j
public class SingletonTest00 {

    // 定义构造方法为私有的
    private SingletonTest00() {}

    private static SingletonTest00 singletonTest00 = new SingletonTest00();

    public static SingletonTest00 getInstance(){
        return singletonTest00;
    }

}
