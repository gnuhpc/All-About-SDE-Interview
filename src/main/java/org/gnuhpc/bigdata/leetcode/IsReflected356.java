package org.gnuhpc.bigdata.leetcode;

import com.google.inject.internal.util.$ImmutableList;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 2019/10/3
 */
public class IsReflected356 {
    /*
    Method1: 判断中间线是不是一致
     */
    public boolean isReflected(int[][] points) {
        if (points == null || points.length == 0) return true;
        if (points.length == 1) return true;
        if (points.length == 2) return points[0][1] == points[1][1];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            List<Integer> list = map.get(point[1]);
            if (list == null) list = new ArrayList<>();
            list.add(point[0]);
            map.put(point[1], list);
        }


        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            Collections.sort(list);
            list = new ArrayList<>(
                    new HashSet<>(list));
            map.put(entry.getKey(), list);
        }

        List<Integer> sample = map.entrySet().iterator().next().getValue();
        double pivot = getPivot(sample);

        if (map.size() == 1) {
            isValidPivot(sample, pivot);

        }
        else {
            System.out.println("Enter map.size() > 1, map is: " + map);
            for (List<Integer> l : map.values()) {
                System.out.println("Pivot is: " + getPivot(l));
                if (pivot != getPivot(l) || !isValidPivot(l, pivot)) {
                    return false;
                }
            }
        }

        return true;
    }

    private double getPivot(List<Integer> list) {
        return list.stream().mapToInt(i -> i).average().getAsDouble();
    }

    private boolean isValidPivot(List<Integer> list, double pivot) {
        if (list.size() % 2 == 0) {
            for (int i = list.size() / 2 - 1, j = list.size() / 2 + 1;
                 i >= 0 && j < list.size(); i--, j++) {
                if (pivot - (double) list.get(i) != (double) list.get(j) - pivot) {
                    return false;
                }
            }
        }
        else {
            System.out.println("List is an odd one");
            return pivot == list.get((list.size() - 1) / 2);
        }

        return true;
    }

    /*
    Method2: 判断x轴的和
   The idea is quite simple. If there exists a line reflecting the points,
   then each pair of symmetric points will have their x coordinates adding up to the same value,
   including the pair with the maximum and minimum x coordinates.
   So, in the first pass, I iterate through the array, adding each point to the hash set,
    and keeping record of the minimum and maximum x coordinates.
     Then, in the second pass, I check for every point to see if
     its symmetric point is in the point set or not.
     If all points pass the test, then there exists a reflecting line. Otherwise, not.

    By the way, here, to hash the content of an array, rather than the reference value,
    I use **Arrays.hashCode(int[])** first, and then re-hash this hash code.
     You can also use **Arrays.toString(int[])** to first convey the 2d array to a string,
      and then hash the string. But the second method is slower.
     */

    public boolean isReflected2(int[][] points) {
        HashSet<Integer> pointSet = new HashSet<>();
        int sum;
        int maxX, minX;

        minX = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        for (int[] point : points) {
            maxX = Math.max(maxX, point[0]);
            minX = Math.min(minX, point[0]);
            pointSet.add(Arrays.hashCode(point));
        }

        sum = maxX + minX;
        for (int[] point : points) {
            if (!pointSet.contains(Arrays.hashCode(new int[]{sum - point[0], point[1]}))) {
                return false;
            }
        }
        return true;
    }


}
