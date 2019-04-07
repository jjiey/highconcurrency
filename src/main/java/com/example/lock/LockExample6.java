package com.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock + Condition
 */
@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        // 线程1
        new Thread(() -> {
            try {
                reentrantLock.lock(); // 此时，线程加入到AQS的等待队列中
                log.info("wait signal"); // 1
                condition.await(); // 此时，线程从AQS的等待队列中移除。对应的操作是锁的释放，然后加入到condition的等待队列里。然后等待着的该线程需要一个信号（才会被从condition的等待队列中移动到AQS的等待队列中）
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4
            reentrantLock.unlock();
        }).start();

        // 线程2
        new Thread(() -> {
            reentrantLock.lock(); // 线程2因为线程1释放锁的关系被唤醒，获取锁，加入到AQS的等待队列中
            log.info("get lock"); // 2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll(); // 发送信号，这时condition等待队列里有线程1的节点，于是被取出来加入到AQS的等待队列里。注意此时线程1没有被唤醒，只是放到了队列里
            log.info("send signal ~ "); // 3
            reentrantLock.unlock(); // 释放锁，之后线程1被唤醒，继续执行
        }).start();
    }
}
