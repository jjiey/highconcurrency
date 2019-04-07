package com.example.immutable;

import com.google.common.collect.Maps;
import com.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * final修饰变量
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a = 2; // 编译就会出错
//        b = "3"; // 编译就会出错
//        map = Maps.newHashMap(); // 编译就会出错
        map.put(1, 3); // 允许修改里面的值
        log.info("{}", map.get(1));
    }

    // 另一种写法
    private void test(final int a) {
//        a = 1; // 编译就会出错
    }
}
