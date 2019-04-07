package com.example.singleton;

import com.annoations.NotRecommend;
import com.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 不推荐，因为加了synchronized关键字，同一时间只允许一个线程访问来保证线程安全，但是却带来了性能上的开销，这个开销并不是我们希望的
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    // 私有构造函数
    private SingletonExample3() {

    }

    // 单例对象
    private static SingletonExample3 instance = null;

    // 静态的工厂方法
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
