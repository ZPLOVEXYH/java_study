package com.java.oracle.study.java_study.concurrent.single;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现单例模式方法（内部类模式，懒加载）
 * 这种方式相比前面两种有所优化，就是使用了lazy-loading。Singleton类被装载了，但是instance并没有立即初始化。
 * 因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，
 * 从而实例化instance。
 */
@Slf4j
public class SingletonTest03 {

    // 定义构造方法为私有的
    private SingletonTest03() {}

    // 内部类可以使用static关键字来修饰
    private static class SingletonHolder {
        private final static SingletonTest03 INSTANCE = new SingletonTest03();
    }

    public static SingletonTest03 getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
