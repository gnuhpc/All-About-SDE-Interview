package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

public class FindBestValue1300 {
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        //如果arr全部替换成arr[n - 1]，仍然比target小，直接返回最大值
        int n = arr.length;
        if((long)arr[n - 1] * n < target){
            return arr[n - 1];
        }


        long[] presum = new long[n+1];
        for(int i = 1; i <= n; i++){
            presum[i] = presum[i-1] + arr[i-1];
        }

        //确定[arr[i], arr[i+1]]
        int start = 1;
        int end = n ;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            long sum = presum[mid] + arr[mid-1] * (n - mid);
            if(sum < target){
                start = mid;
            }else{
                end = mid;
            }
        }

        long pre = presum[start];
        //有多少个元素需要被替换
        int count = n - (start-1);

        //如果arr全部替换成arr[0]，仍然比target大，则val搜索范围为[0, arr[0]]
        if((long)arr[0] * n > target){
            start = 0;
            end = arr[0];
            pre = 0;
        }else{
            //general case: 搜索范围[arr[start], arr[end]]，即[arr[start], arr[start + 1]]
            start = arr[start-1];
            end = arr[end-1];
            count -= 1;
        }

        //二分查找val
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            long sum = pre + count * mid;
            if(sum < target){
                start = mid;
            }else{
                end = mid;
            }
        }

        long startSum = pre + count * start;
        long endSum = pre + count * end;
        if(Math.abs(target - startSum) <= Math.abs(target - endSum)){
            return start;
        }else{
            return end;
        }
    }
}
