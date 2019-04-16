package com.java.oracle.study.java_study.concurrent;

/**
 * 一、volatile 关键字：当多个线程进行操作共享数据时，可以保证线程间共享数据的可见性。
 * 相比与synchronized 是一种较为轻量级的同步策略。
 * <p>
 * 注意：
 * 1. volatile 不具备 "互斥性"
 * 2. volatile 不能保证变量的原子性
 */
public class TestVolatile {

    public static void main(String[] args) {
//        ThreadDemo td = new ThreadDemo();
//        new Thread(td).start();
//
//        while(true) {
//            synchronized (td) {
//                if(td.isFlag()){
//                    System.out.println("-------------");
//                    break;
//                }
//            }
//        }

        int i = 10;
        i = i++;
        System.out.println("i = " + i);
    }
}
