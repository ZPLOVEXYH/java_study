package com.java.oracle.study.java_study.concurrent.cas;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFutureTask {

    public static void main(String[] args) {
        FutureTaskDemo ftd = new FutureTaskDemo();
        FutureTask<Integer> ft = new FutureTask(ftd);
        new Thread(ft).start();

        try {
            Integer sum = ft.get();
            System.out.println(sum);
            System.out.println("--------------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class FutureTaskDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int num = 0;
        for (int i = 0; i < 100; i++) {
            num = num + i;
        }
        return num;
    }
}
