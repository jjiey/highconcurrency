package com.example.threadLocal;

/**
 * 线程封闭ThreadLocal
 * 一般配合Filter使用
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    // 如果不做移除会造成内存泄漏，数据永远不会释放掉
    public static void remove() {
        requestHolder.remove();
    }
}
