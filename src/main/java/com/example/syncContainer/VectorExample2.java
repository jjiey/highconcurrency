package com.example.syncContainer;

import com.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * Vector线程不安全的情况
 * Vector虽然能保证同一个时刻只有一个线程能访问它，但是不排除当某个线程在某个时刻调用get方法时，另一个线程正好将这个值remove掉了，这时就会出现ArrayIndexOutOfBoundsException。需要使用额外的同步措施
 */
@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i); // ArrayIndexOutOfBoundsException
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i); // ArrayIndexOutOfBoundsException
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
