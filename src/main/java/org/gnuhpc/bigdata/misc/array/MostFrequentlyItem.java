package org.gnuhpc.bigdata.misc.array;

import scala.Tuple2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MostFrequentlyItem {
    public static void main(String[] args) {
        final int size = 6;
        int[] array = new int[6];
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            array[i] = in.nextInt();
        }

        Tuple2<Integer, Integer> item = findMostFrequentlyItem(array);
        System.out.println(
                String.format("The most frequently item is %d, shown %d times",item._1,item._2)
        );
    }

    private static Tuple2<Integer,Integer> findMostFrequentlyItem(int[] array) {
        int maxIdx = 0;
        int maxTimes = 0;
        Map<Integer,Integer> occurrenceMap = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            Integer times = occurrenceMap.getOrDefault(array[i], 0);
            occurrenceMap.put(array[i],++times);
            if(times>maxTimes){
                maxTimes = times;
                maxIdx = i;
            }
        }

        return new Tuple2<>(array[maxIdx],maxTimes);
    }
}
