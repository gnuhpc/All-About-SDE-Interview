package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/solution/jin-jie-san-wen-by-user5707f/
public class InterSectionTwoArrays350 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        for (int i : intersection(nums1, nums2)) {
            System.out.println(i);
        }
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        List<Long> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Map<Integer, Long> map1 = Arrays.stream(nums1).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (int i : nums2) {
            if (map1.containsKey(i) && map1.get(i) > 0) {
                result.add((long) i);
                Long value = map1.get(i);
                map1.put(i, value - 1);
            }
        }
        return result.stream().mapToInt(Number::intValue).toArray();
    }
}
