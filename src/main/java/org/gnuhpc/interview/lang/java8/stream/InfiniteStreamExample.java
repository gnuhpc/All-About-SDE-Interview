package org.gnuhpc.interview.lang.java8.stream;

import java.util.stream.Stream;

/**
 * Created by gnuhpc on 2017/1/16.
 */
public class InfiniteStreamExample {
    public static void main(String[] args) {
        Stream.generate(() -> "timestamp:" + System.currentTimeMillis()).forEach(System.out::println);
    }
}
