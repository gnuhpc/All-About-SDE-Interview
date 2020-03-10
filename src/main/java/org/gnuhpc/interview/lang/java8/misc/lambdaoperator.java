package org.gnuhpc.interview.lang.java8.misc;

import java.util.function.BinaryOperator;

/**
 * Created by gnuhpc on 2017/1/5.
 */
public class lambdaoperator {
    public static void main(String[] args) {
        BinaryOperator<Long> addAndMulti = (x, y) -> (x + y) * x * y;
        System.out.println(addAndMulti.apply(1L, 2L));
    }
}
