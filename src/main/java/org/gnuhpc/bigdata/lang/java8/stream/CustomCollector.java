package org.gnuhpc.bigdata.lang.java8.stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by gnuhpc on 2017/1/16.
 */
public class CustomCollector<T> implements Collector<T, Map<T, Integer>, Optional<T>> {

    @Override
    public Supplier<Map<T, Integer>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<T, Integer>, T> accumulator() {
        return (acc, elem) -> acc.merge(elem, 1, (old, v) -> old + v);
    }

    @Override
    public BinaryOperator<Map<T, Integer>> combiner() {
        return null;
    }

    @Override
    public Function<Map<T, Integer>, Optional<T>> finisher() {
        return (acc) -> acc.entrySet().stream()
                .reduce((a, b) -> a.getValue() > b.getValue() ? a : b)
                .map(Map.Entry::getKey);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }


    public static void main(String[] args) {
        Arrays.asList(1, 1, 2, 2, 2, 3, 4, 5, 5)
                .stream().collect(new CustomCollector<>()).ifPresent(System.out::println);
    }
}
