package org.gnuhpc.bigdata.systemdesign.practice.lru;

import java.util.*;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxEntries;

    public LRUCache(int maxEntries) {
        super(maxEntries);
        this.maxEntries = maxEntries;
    }


    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxEntries;
    }
}

