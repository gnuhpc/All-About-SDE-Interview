package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoSum1 {
    public static void main(String[] args) {
        int[] nums = new int[] {2, 7, 11, 15};
        int target = 9;

        twoSumUsingSort(nums,target);

        Arrays.stream(twoSum(nums,target)).forEach(System.out::println);
    }

    // construct distanceMap <num, idx>
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> recordsMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int a = nums[i], b = target - nums[i];
            if(recordsMap.containsKey(b)){
                result[0] = i;
                result[1] = recordsMap.get(b);
            } else {
                recordsMap.put(a,i);
            }
        }
        return result;
    }


    //Method 2: using sort
    /*
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    static class Pair {
        Integer value;
        Integer index;

        Pair(Integer value, Integer index) {
            this.value = value;
            this.index = index;
        }
        Integer getValue() {
            return this.value;
        }
    }

    static class ValueComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }
    public static int[] twoSumUsingSort(int[] numbers, int target) {
        int[] result = new int[2];
        // write your code here

        Pair[] number = new Pair[numbers.length];
        for(int i=0;i<numbers.length;i++) {
            number[i] = new Pair(numbers[i], i);
        }
        Arrays.sort(number, new ValueComparator());
        int L=0, R = numbers.length-1;
        while(L<R) {
            if( number[L].getValue() + number[R].getValue() == target) {
                result[0] = number[L].index;
                result[1] = number[R].index;
                return result;
            }
            if( number[L].getValue() + number[R].getValue() < target) {
                L++;
            } else {
                R--;
            }
        }
        return result;
    }
}
