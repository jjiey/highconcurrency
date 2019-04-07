package com.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newScheduledThreadPool
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {

        // 传入corePoolSize为1的话就和Timer很像了
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        // case1：指定运行时间参数，延迟3秒执行任务
//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        }, 3, TimeUnit.SECONDS);
        // replace with lambda
//        executorService.schedule(() -> log.warn("schedule run"), 3, TimeUnit.SECONDS);

        // case2：指定运行时间参数，延迟1秒后每隔3秒执行一次任务
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        }, 1, 3, TimeUnit.SECONDS);
        // replace with lambda
        executorService.scheduleAtFixedRate(() -> log.warn("schedule run"), 1, 3, TimeUnit.SECONDS);
//        executorService.shutdown(); // 任务按照一定的调度不停的执行，因此不调用shutdown()方法。如果要关闭，需要等到某一个契机之后调用这个实例的shutdown()方法进行关闭

        Timer timer = new Timer();
        // case3
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                log.warn("timer run");
//            }
//        }, new Date());
        // case4：每次执行完之后，间隔5秒继续执行
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        }, new Date(), 5 * 1000);
    }
}
