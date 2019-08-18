package com.example.queue;

import lombok.AllArgsConstructor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue
 * 容器里每一个元素都设置了一个时间，时间到了才能从中提取元素
 */
public class DelayQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<MyTask> tasks = new DelayQueue<>();
        // 创建延迟任务
        long now = System.currentTimeMillis();
		tasks.put(new MyTask("1", now + 1000));
		tasks.put(new MyTask("2", now + 2000));
		tasks.put(new MyTask("3", now + 1500));
		tasks.put(new MyTask("4", now + 2500));
		tasks.put(new MyTask("5", now + 500));
		System.out.println(tasks);
		// 取出任务并打印
		for (int i = 0; i < 5; i++) {
			System.out.println(tasks.take());
		}
	}

	@AllArgsConstructor
	static class MyTask implements Delayed {
        private final String taskName;
		private final long runningTime;

		@Override
		public int compareTo(Delayed o) {
			if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MICROSECONDS)) {
				return -1;
			}else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
				return 1;
			}else {
				return 0;
			}
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}
		
		@Override
		public String toString() {
			return taskName + " : " + runningTime;
		}
	}

}
