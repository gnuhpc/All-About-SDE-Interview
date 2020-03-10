package org.gnuhpc.interview.algorithm.recursion.combinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations {

    /**
     * Generates all combinations and output them,
     * selecting n elements from data.
     */
    public void combinations(
            List<Integer> selected, List<Integer> data, int n) {
        if (n == 0) {
            // output all selected elements
            selected.forEach(i -> System.out.print(i + " "));
            System.out.println();
            return;
        }

        if (data.isEmpty()) {
            return;
        }

        // select element 0
        selected.add(data.get(0));
        combinations(selected, data.subList(1, data.size()), n - 1);

        // un-select element 0
        selected.remove(selected.size() - 1);
        combinations(selected, data.subList(1, data.size()), n);
    }

    //Back tracing + recursition
    public void combinations(
            List<Integer> selected, int start, List<Integer> data, int n) {
        if (selected.size() == n) {
            selected.forEach(i -> System.out.print(i + " "));
            System.out.println();
            return;
        }

        for (int i = start; i < data.size(); i++) {
            selected.add(data.get(i));
            //如果你想得到一个重复的，则需要采用combinations(selected,start+1, data, n);
            combinations(selected, i + 1, data, n);
            selected.remove(selected.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combinations comb = new Combinations();

        comb.combinations(new ArrayList<>(), 0, Arrays.asList(1, 2, 3, 4), 2);

        System.out.println("Testing normal data.");
        comb.combinations(
                new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 2);
        System.out.println("==========");
//
//    System.out.println("Testing empty source data.");
//    comb.combinations(
//        new ArrayList<>(), new ArrayList<>(), 2);
//    System.out.println("==========");
//    comb.combinations(
//        new ArrayList<>(), new ArrayList<>(), 0);
//    System.out.println("==========");
//
//    System.out.println("Selecting 1 and 0 elements.");
//    comb.combinations(
//        new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 1);
//    System.out.println("==========");
//    comb.combinations(
//        new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 0);
//    System.out.println("==========");
//
//    System.out.println("Testing large data");
//    comb.combinations(
//        new ArrayList<>(),
//        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 4);
    }
}
