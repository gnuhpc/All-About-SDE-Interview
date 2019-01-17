package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LengthOfLIS300 {
    /*
    暴力搜索1
     */

    public int lengthOfLIS(int[] nums) {
        return lengthofLIS(nums, Integer.MIN_VALUE, 0);
    }

    /*
    we consider two cases:

The current element is larger than the previous element included in the LIS.
In this case, we can include the current element in the LIS.
 Thus, we find out the length of the LIS obtained by including it.
 Further, we also find out the length of LIS possible by not including the current element in the LIS.
 The value returned by the current function call is,
 thus, the maximum out of the two lengths.

The current element is smaller than the previous element included in the LIS.
In this case, we can't include the current element in the LIS.
Thus, we find out only the length of the LIS possible by not including the current element in the LIS,
 which is returned by the current function call.
     */
    public int lengthofLIS(int[] nums, int prev, int curpos) {
        if (curpos == nums.length) {
            return 0;
        }
        int taken = 0, nottaken = 0;
        if (nums[curpos] > prev) {
            taken = 1 + lengthofLIS(nums, nums[curpos], curpos + 1);
        }
        nottaken = lengthofLIS(nums, prev, curpos + 1);
        return Math.max(taken, nottaken);
    }

    /*
    Method2: DP
     */
    //https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
    // 如果要求这个子序列，则参考：
    // https://www.hackerrank.com/challenges/longest-increasing-subsequent/problem
    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        if (n==0){ return 0;}
        int lis[] = new int[n];
        int i,j;

        /* Initialize LIS values for all indexes */
        for ( i = 0; i < n; i++ )
            lis[i] = 1;

        /* Compute optimized LIS values in bottom up manner */
        for ( i = 1; i < n; i++ )
            for ( j = 0; j < i; j++ )
                if ( nums[i] > nums[j] )
                    lis[i] = Math.max(lis[i], lis[j] + 1);

        /* Pick maximum of all LIS values */

        return Arrays.stream(lis).max().getAsInt();
    }


    //二分法
    public int lengthOfLIS3(int[] nums) {
        if(nums.length == 0) return 0;
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i = 1; i < nums.length; i++){
            //遇到比头元素小的数字替换
            if(nums[i] < list.get(0)) list.set(0, nums[i]);
                //遇到比尾元素打的数字添加到末尾
            else if(nums[i] > list.get(list.size() - 1)) list.add(nums[i]);
                //如果遍历到的新元素比ends数组首元素大，比尾元素小时，
                //用二分查找法找到第一个不小于此新元素的位置，覆盖掉位置的原来的数字
            else{
                int left = 0, right = list.size() - 1;
                while(left < right){
                    int mid = (left + right) / 2;
                    if(nums[i] <= list.get(mid)) right = mid;
                    else left = mid + 1;
                }
                list.set(left, nums[i]);
            }
        }
        return list.size();
    }

    @Test
    public void test(){
        System.out.println(lengthOfLIS2(new int[]{10,9,2,5,3,7,101,18}));
    }
}
