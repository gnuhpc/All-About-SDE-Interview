package org.gnuhpc.interview.designpattern.retry;

@FunctionalInterface
public interface ExponentialBackOffFunction<T> {
    T execute() throws Exception;
}
