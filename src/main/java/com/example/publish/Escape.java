package com.example.publish;

import com.annoations.NotRecommend;
import com.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象逸出
 * 在对象没有被正确构造完成之前就会被发布，有可能有不安全的因素。
 * 一个是导致this引用在构造期间逸出的错误，它是在构造函数过程中（public Escape ()）相当于启动了一个线程，无论是隐式的启动还是显示的启动都会造成this引用的逸出，新线程总会在所属对象构造完毕之前就已经看到它。所以如果要在构造函数中创建线程，那么不要启动它，而是应该采用一个专有的start或初始化的方法来统一启动线程。
 * 这里其实可以采用静态工厂方法和私有构造函数来完成对象创建和监听器的注册等，这样才可以避免不正确的创建。参见单例
 * 我们的目的是在对象未完成构造之前不可以将其发布
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
