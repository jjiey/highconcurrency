package com.example.queue;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * PriorityBlockingQueue
 * 带优先级的，无界，阻塞队列
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        // 创建任务，并添加到队列
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setPriority(random.nextInt(10));
            task.setTaskName("taskName" + i);
            queue.offer(task);
        }
        // 取出任务执行
        while (!queue.isEmpty()) {
            Task task = queue.poll();
            if (null != task) {
                task.doSomeThing();
            }
        }
    }

    @Getter
    @Setter
    static class Task implements Comparable<Task> {
        private int priority = 0;
        private String taskName;

        @Override
        public int compareTo(Task o) {
            if (this.priority >= o.getPriority()) {
                return 1;
            } else {
                return -1;
            }
        }

        public void doSomeThing() {
            System.out.println(taskName + " : " + priority);
        }
    }

}
