package com.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {

    public static final int threshold = 2; // 相当于判断任务大小的阈值
    private int start; // 相当于main里传入的1
    private int end; // 相当于main里传入的100

    public ForkJoinTaskExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // 真正做Fork和Join操作
    @Override
    protected Integer compute() {
        int sum = 0;

        // 判断任务大小
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            // 如果任务足够小就直接计算任务
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2; // 取出中间值
            // 拆分子任务
            ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start, middle);
            ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkjoinPool = new ForkJoinPool();

        // 生成一个计算任务，计算1+2+3+4
        // 传入两个值代表加和要从1加到100
        ForkJoinTaskExample task = new ForkJoinTaskExample(1, 100);

        // 执行一个任务，会调用compute()
        Future<Integer> result = forkjoinPool.submit(task);

        try {
            log.info("result:{}", result.get());
        } catch (Exception e) {
            log.error("exception", e);
        }
    }
}
