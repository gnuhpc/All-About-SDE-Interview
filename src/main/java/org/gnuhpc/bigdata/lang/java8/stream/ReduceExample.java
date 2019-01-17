package org.gnuhpc.bigdata.lang.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * Created by gnuhpc on 2017/1/16.
 */
public class ReduceExample {

    private  static BinaryOperator<Integer> COMBINER = (a, b) -> {
        System.out.printf("combining(%s, %s)%n", a, b);
        return Integer.sum(a, b);
    };
    public static void main(String[] args) {
        int[] array = {23,43,56,97,32};
        //By default start value is 0. Result will be sum of array.
        Arrays.stream(array).reduce((x, y) -> x+y).ifPresent(s -> System.out.println(s));
        Arrays.stream(array).reduce(Integer::sum).ifPresent(s -> System.out.println(s));
        Arrays.stream(array).reduce(StatisticsUtility::addIntData).ifPresent(s -> System.out.println(s));

        //Set start value. Result will be start value + sum of array.
        int startValue = 100;
        int sum = Arrays.stream(array).reduce(startValue, (x,y) -> x+y);
        System.out.println(sum);
        sum = Arrays.stream(array).reduce(startValue, Integer::sum);
        System.out.println(sum);
        sum = Arrays.stream(array).reduce(startValue, StatisticsUtility::addIntData);
        System.out.println(sum);

        //reduce(T initial, BiFunction accumulator, BinaryOperator combiner)
        List<Integer> list2 = Arrays.asList(2, 3, 4);
//        //Here result will be 2*2 + 2*3 + 2*4 that is 18.
        int res = list2.parallelStream().reduce(2, (s1, s2) -> s1 * s2, (p, q) -> p + q);
        System.out.println(res);

        List<String> list1 = Arrays.asList("Mohan", "Sohan", "Ramesh");
        String result = list1.parallelStream().reduce("-", (s1, s2) -> s1 + s2, (p, q) -> p + q);
        System.out.println(result);

        //Reduce Array to String.
        String[] array1 = {"Mohan", "Sohan", "Mahesh"};
        Arrays.stream(array1).reduce((x, y) -> x +"," + y)
                .ifPresent(s -> System.out.println("Array to String: "+ s));
        //Reduce List to String.
        List<String> list = Arrays.asList("Mohan", "Sohan", "Mahesh");
        list.stream().reduce((x, y) -> x +"," + y)
                .ifPresent(s -> System.out.println("List to String: "+ s));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        sum = numbers.stream()
                // init, accumulator, combiner
                .reduce(0, Integer::sum, COMBINER);
        System.out.println("Sum: " + sum);

        sum = numbers.parallelStream()
                // init, accumulator, combiner
                .reduce(0, Integer::sum, COMBINER);
        System.out.println("Sum: " + sum);

    }
}
