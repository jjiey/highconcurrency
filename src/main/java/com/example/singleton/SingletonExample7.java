package com.example.singleton;

import com.annoations.Recommend;
import com.annoations.ThreadSafe;

/**
 * 枚举模式：最安全
 * 相比于懒汉模式在安全性方面更容易保证，相比于饿汉模式在实际调用的时候才会做最开始的初始化，而在后续使用的时候也可以直接取到里面的值，不会造成资源的浪费
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有构造函数
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    // 私有枚举类
    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
