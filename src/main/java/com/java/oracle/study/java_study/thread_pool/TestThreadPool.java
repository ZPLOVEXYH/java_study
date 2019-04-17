package com.java.oracle.study.java_study.thread_pool;

import lombok.extern.slf4j.Slf4j;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

@Slf4j
public class TestThreadPool {

    public static void main(String[] args) {
        // 方式一
//        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(threadPoolDemo);
//        log.info("test main");
//        executorService.shutdown();

        // 方式二
//        ThreadPoolDemo tpd = new ThreadPoolDemo();
//        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
//        executorService.execute(tpd);
//        log.info("test main");
//        executorService.shutdown();

        FutureDemo fd = new FutureDemo();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(fd);
        try {
            log.info(future.get());
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

@Slf4j
class ThreadPoolDemo implements Runnable {

    @Override
    public void run() {
        log.info("test ThreadPoolDemo");
    }
}

class FutureDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "test future";
    }
}
