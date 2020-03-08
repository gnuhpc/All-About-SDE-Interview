package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
本题解题前可以去了解一下时序数据库，本题其实就是实现一个简单的时序数据库。同时实现也是很简单：

首先定义一个ValueMap，该Map以timestamp为key，以value为value，这样就能够记录下不同timestamp下的value。
然后再定义一个Map,该Map以key为key，以ValueMap为value。
需要注意ValueMap我选择的是用TreeMap，其提供firstKey()能够快速找到第一个元素，而floorEntry()则会返回与小于或等于给定键的最大键关联的键值映射，如果没有这样的键，则返回null。
 */

public class TimeMap9812 {
    //比较典型的一个使用treemap 的方法
    private Map<String, TreeMap<Integer, String>> map;

    /**
     * Initialize your data structure here.
     */
    public TimeMap9812() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> tmap = map.get(key);
        if (null == tmap) {
            return "";
        }
        Integer floorKey = tmap.floorKey(timestamp);
        if (null == floorKey) {
            return "";
        }
        return tmap.get(floorKey);
    }

}
