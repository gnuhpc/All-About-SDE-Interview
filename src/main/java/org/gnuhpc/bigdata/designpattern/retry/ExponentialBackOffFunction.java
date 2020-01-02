package org.gnuhpc.bigdata.designpattern.retry;

@FunctionalInterface
public interface ExponentialBackOffFunction<T> {
    T execute() throws Exception;
}
