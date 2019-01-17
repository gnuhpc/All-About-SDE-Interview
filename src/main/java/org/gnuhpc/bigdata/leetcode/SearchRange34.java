package org.gnuhpc.bigdata.leetcode;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-21 22:04
 * Version: 0.1
 */
public class SearchRange34 {
    public int[] searchRange(int[] nums, int target) {

        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if(nums.length==0) return res;

        int start = 0, end = nums.length -1;

        while( start + 1 < end){
            int mid = (end - start)/2 + start;

            if ( nums[mid] == target) end = mid;
            else if ( nums[mid] > target) end = mid;
            else start = mid;
        }

        if(nums[start] == target) res[0] = start;
        else if(nums[end] == target) res[0] = end;
        else res[0] = -1;
        int i = res[0];

        while(i+1 < nums.length && nums[i+1] == target){
            i++;
        }

        res[1] = i;

        return res;
    }
}
