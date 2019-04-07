package com.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors.newSingleThreadExecutor
 * 单线程执行，会按顺序执行，不会乱序
 */
@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int index = i;
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    log.info("task:{}", index);
//                }
//            });
            // replace with lambda
            executorService.execute(() -> log.info("task:{}", index));
        }
        executorService.shutdown();
    }
}
