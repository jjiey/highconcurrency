package com.example.singleton;

import com.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * 如果单例类的构造方法中没有包含过多的操作处理，饿汉模式其实是可以接受的。
 * 不足是如果构造方法中存在过多的处理，会导致这个类在加载的时候特别慢，因此可能会引起性能问题；另外如果使用饿汉模式只进行类的加载而没有实际的调用，还会造成资源的浪费
 * 因此使用饿汉模式要考虑两个问题：1.私有构造函数在实现的时候没有太多的处理；2.这个类在实际的过程中肯定会被使用
 */
@ThreadSafe
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2() {

    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
