package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TwoSum1 {
    public static void main(String[] args) {
        int[] nums = new int[] {7, 11, 2, 15};
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
     * @return: [index1 , index2 ] (index1 < index2)
     */
    //Pair为值和索引的封装，以
    static class Pair implements Comparable {
        Integer value;
        Integer index;

        Pair(Integer value, Integer index) {
            this.value = value;
            this.index = index;
        }
        Integer getValue() {
            return this.value;
        }

        @Override
        public int compareTo(Object o) {
            return this.value.compareTo(((Pair) o).value);
        }
    }

    public static int[] twoSumUsingSort(int[] numbers, int target) {
        int[] result = new int[2];
        // write your code here

        Pair[] number = new Pair[numbers.length];
        for(int i=0;i<numbers.length;i++) {
            number[i] = new Pair(numbers[i], i);
        }
        Arrays.sort(number);
        int L=0, R = numbers.length-1;
        while(L<R) {
            if( number[L].getValue() + number[R].getValue() == target) {
                result[0] = number[L].index;
                result[1] = number[R].index;
                return result;
            }
            //注意这个移动规则，给定了一边后再移动，且指针只向一个方向移动
            if( number[L].getValue() + number[R].getValue() < target) {
                L++;
            } else {
                R--;
            }
        }
        return result;
    }
}
