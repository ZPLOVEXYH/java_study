package com.java.oracle.study.java_study.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskTest {

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in FutureTest!!!");

                Thread.sleep(5000);
                return "future done";
            }
        });

        new Thread(futureTask).start();
        log.info("do somathing in main!!!");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("返回的结果：{}", result);

    }
}
