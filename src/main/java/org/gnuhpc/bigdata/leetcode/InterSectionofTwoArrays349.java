package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InterSectionofTwoArrays349 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        for (int i:intersection(nums1,nums2)){
            System.out.println(i);
        }
    }
    public static int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length==0 || nums2.length==0) {
            return new int[0];
        }
        Set<Integer> set1 = Arrays.stream(nums1).mapToObj(i-> new Integer(i)).collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).mapToObj(i-> new Integer(i)).collect(Collectors.toSet());
        set1.retainAll(set2);
        return set1.stream().mapToInt(i->new Integer(i)).toArray();
    }
}
