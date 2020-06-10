package org.gnuhpc.interview.systemdesign.practice.cache;

/**
 * Copyright gnuhpc 2020/6/1
 */
public interface Cache {
    void add(String key, Object value, long periodInMillis);

    void remove(String key);

    Object get(String key);

    void clear();

    long size();
}
