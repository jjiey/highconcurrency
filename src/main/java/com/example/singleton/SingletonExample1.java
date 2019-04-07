package com.example.singleton;

import com.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 单线程模式下没问题，多线程环境下可能会有问题，问题出在（if (instance == null) ）行，可能会实例化多次，这样也是有影响的，因为我们无法避免如果在实例的时候私有构造函数要做很多操作，包括一些对资源的处理或者运算，这时如果运算两次就可能会出现错误
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {

    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态的工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
