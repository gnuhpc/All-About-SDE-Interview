package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.HashSet;


public class FirstMissingPositive41 {
    // 题目要求o(n)的时间复杂度，且O(1)的空间复杂度
    // 一种类似于桶排序的思想
    // 结果值在[1~n+1]之间
    /*  这道题之所有能利用本来的数组作为bucket的关键原因是因为如果里面的值都小于等于0或者都大于n，则一定是1
        可以用数组本身作为一个"hash table"：A[0] = 1, A[1] = 2, .... A[n-1] = n。目标是尽可能将数字i放到数组第i-1个位置。
        扫描数组中每个数：
        1. 如果A[i]<1或者A[i]>n。说明A[i]一定不是first missing positive。跳过
        2. 如果A[i] = i+1，说明A[i]已经在正确的位置，跳过
        3. 如果A[i]!=i+1，且0<A[i]<=n，应当将A[i]放到A[A[i]-1]的位置，所以可以交换两数。
        这里注意，当A[i] = A[A[i]-1]时会陷入死循环。这种情况下直接跳过。
     */

    //2ms, beat 89.18%
    public int firstMissingPositive(int[] nums) {
        if (nums == null) return -1;


        //基于上边的关键原因，可以做如下优化
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

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
