package com.java.oracle.study.java_study.concurrent.single;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现单例模式方法（饿汉模式2）
 */
@Slf4j
public class SingletonTest01 {

    // 定义构造方法为私有的
    private SingletonTest01() {}

    private static SingletonTest01 singletonTest01;

    static {
        singletonTest01 = new SingletonTest01();
    }

    public static SingletonTest01 getInstance(){
        return singletonTest01;
    }

}
