package org.gnuhpc.bigdata.lang.java8.misc;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ClosureExample {
    public static Function<Integer, Integer> closure() {
        int a=3;
        Function<Integer, Integer> function = t->{
            return t*a; // a is available to be accessed in this function
        };
        return function;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Function<Integer, Integer> closure = ClosureExample.closure();
        list.stream().map(closure).forEach(n -> System.out.print(n+" "));
    }

}
