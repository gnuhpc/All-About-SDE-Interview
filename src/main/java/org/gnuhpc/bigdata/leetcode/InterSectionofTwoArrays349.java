package org.gnuhpc.bigdata.leetcode;

import java.util.*;
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
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        set1.retainAll(set2);
        return set1.stream().mapToInt(Integer::new).toArray();
    }

    // version1：sort & merge
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
    public int[] intersection1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        int[] temp = new int[nums1.length];
        int index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (index == 0 || temp[index - 1] != nums1[i]) {
                    temp[index++] = nums1[i];
                }
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] result = new int[index];
        for (int k = 0; k < index; k++) {
            result[k] = temp[k];
        }

        return result;
    }

    // version2 hash memo
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }

        HashSet<Integer> hash = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            hash.add(nums1[i]);
        }

        HashSet<Integer> resultHash = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if (hash.contains(nums2[i]) && !resultHash.contains(nums2[i])) {
                resultHash.add(nums2[i]);
            }
        }

        int size = resultHash.size();
        int[] result = new int[size];
        int index = 0;
        for (Integer num : resultHash) {
            result[index++] = num;
        }

        return result;
    }

    // version 3: sort & binary search
    public int[] intersection3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }

        HashSet<Integer> set = new HashSet<>();

        Arrays.sort(nums1);
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                continue;
            }
            if (binarySearch(nums1, nums2[i])) {
                set.add(nums2[i]);
            }
        }

        int[] result = new int[set.size()];
        int index = 0;
        for (Integer num : set) {
            result[index++] = num;
        }

        return result;
    }

    private boolean binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (nums[start] == target) {
            return true;
        }
        if (nums[end] == target) {
            return true;
        }

        return false;
    }

    // TreeSet
    public int[] intersection4(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i]) ) {
                arr.add(nums2[i]);
                set.remove(nums2[i]);
            }
        }

        int size = arr.size();
        int[] result = new int[size];
        int index = 0;
        for (Integer num : arr) {
            result[index++] = num;
        }

        return result;
    }

}
