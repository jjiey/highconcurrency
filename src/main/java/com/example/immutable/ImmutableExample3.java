package com.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.annoations.ThreadSafe;

import java.util.List;

/**
 * ImmutableXXX
 */
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
    private final static List<Integer> list2 = ImmutableList.of(1, 2, 3); // 可以达到相同的效果

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);

    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
            .put(1, 2).put(3, 4).put(5, 6).build();

    public static void main(String[] args) {
//        list.add(4); // Deprecated UnsupportedOperationException
//        list2.add(4); // UnsupportedOperationException
//        set.add(4); // Deprecated UnsupportedOperationException
//        map.put(1, 4); // Deprecated UnsupportedOperationException
//        map2.put(1, 4); // Deprecated UnsupportedOperationException
        System.out.println(map2.get(3));
    }
}
