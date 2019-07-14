package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.HashSet;


public class FirstMissingPositive41 {
    // 题目要求o(n)的时间复杂度，且O(1)的空间复杂度
    // 一种类似于桶排序的思想
    // 结果值在[1~n+1]之间
    public int firstMissingPositive(int[] nums) {
        if (nums == null) return -1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] <= nums.length && nums[i] > 0 && nums[nums[i] - 1] != nums[i]){
                Utils.swap(nums, nums[i]-1,i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i +1){
                return i+1;
            }
        }
        return nums.length + 1;
    }

    // add by Tina，一个不符合题意对空间和时间的要求，但是是常规能想到的暴力解法
    // 创建一个HashSet，遍历一遍数组，将元素放到HashSet，找到最大值。
    // 再遍历一遍数组，从1开始寻找，找不到就返回
    public int firstMissingPositive2(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        int n = nums.length;
        for(int i = 0; i<n;i++){
            hs.add(nums[i]);
        }

        for(int i = 1; i<=n;i++){
            if(!hs.contains(i)) return i;
        }

        return n+1;

    }

    // 效率上看，这个方法和上面的差别不大
    public int firstMissingPositive3(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        int n = nums.length;
        int mx = 0;
        for(int i = 0; i<n;i++){
            hs.add(nums[i]);
            mx = Math.max(mx,nums[i]);
        }

        for(int i = 1; i<=mx;i++){
            if(!hs.contains(i)) return i;
        }

        return mx+1;

    }

    @Test
    public void test(){
        System.out.println(firstMissingPositive(new int[]{7,8,9,11,12}));
        System.out.println(firstMissingPositive(new int[]{3,4,-1,1}));
    }

}
