package com.java.oracle.study.java_study.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureTest {

    static class FutureTestDemo implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something in FutureTest!!!");

            Thread.sleep(5000);
            return "future done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newCachedThreadPool();
        Future<String> future = es.submit(new FutureTestDemo());
        log.info("do something in main!!!");
        Thread.sleep(1000);
        String result = future.get();

        log.info("返回的结果： {}", result);
    }
}
