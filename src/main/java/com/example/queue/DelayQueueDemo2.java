package com.example.queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue
 */
public class DelayQueueDemo2 {

	public static void main(String[] args) {
		// 创建delay队列
		DelayQueue<DelayedEle> queue = new DelayQueue<>();
		// 创建延迟任务
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			queue.offer(new DelayedEle(random.nextInt(500), "task" + i));
		}
		// 依次取出任务并打印
		DelayedEle ele;
		try {
			// 循环，如果想避免虚假唤醒，则不能把全部元素都打印出来
			for(;;) {
				// 获取过期任务并打印
				while ((ele = queue.take()) != null) {
					System.out.println(ele);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class DelayedEle implements Delayed {
		// 延迟时间
		private final long delayTime;
		// 到期时间
		private final long expire;
		// 任务名称
		private final String taskName;

		public DelayedEle(long delayTime, String taskName) {
			this.delayTime = delayTime;
			this.taskName = taskName;
			expire = System.currentTimeMillis() + delayTime;
		}

		/**
		 * 剩余时间 = 到期时间 - 当前时间
		 */
		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		/**
		 * 优先级队列里面的优先级规则
		 */
		@Override
		public int compareTo(Delayed o) {
			return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("DelayedEle = {");
			sb.append("delayTime = \'").append(this.delayTime);
			sb.append("\', expire = \'").append(this.expire);
			sb.append("\', taskName = \'").append(this.taskName).append("\'");
			sb.append("}");
			return sb.toString();
		}
	}

}
