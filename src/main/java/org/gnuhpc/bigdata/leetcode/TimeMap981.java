package org.gnuhpc.bigdata.leetcode;

import java.util.*;

/*
用Treemap也可以，因为只需要存两个值，正好key-value
 */
public class TimeMap981 {
    class Tuple implements Comparable<Tuple>{
        String value;
        int timestamp;

        public Tuple(String value, int timestamp){
            this.value = value;
            this.timestamp = timestamp;
        }

        @Override
        public int compareTo(Tuple o) {
            return o.timestamp - this.timestamp;
        }
    }
    /** Initialize your data structure here. */
    Map<String, TreeSet<Tuple>> cache;
    public TimeMap981() {
        cache = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        cache.computeIfAbsent(key,
                k->(new TreeSet<>()))
                .add(new Tuple(value,timestamp));
    }

    public String get(String key, int timestamp) {
        if (!cache.containsKey(key)) return "";
        else{
            return cache.get(key).floor(new Tuple("",timestamp)).value;
        }
    }
}
