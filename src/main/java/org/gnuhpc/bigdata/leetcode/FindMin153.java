package org.gnuhpc.bigdata.leetcode;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-23 9:45
 * Version: 0.1
 */
//画个图就行知道了。target用nums[0]也行
    /*
    if the array is indeed rotated by some pivot, there are only 2 possibilities

> 1. a[mid] > a[left] && a[mid] > a[right], meaning we are on the bigger part, the smaller part is on our right, so go right

> 2. a[mid] < a[left] && a[mid] < a[right], meaning we are on the smaller part, to find the smallest element, go left

if the array is not rotated (actually one rotating cycle completed), we just need to go left, in this case a[mid] < a[right] always holds.

     */
public class FindMin153 {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }

        int start = 0, end = nums.length-1;

        // find the first element <= target
        while (start + 1 < end) {
            //注意这个锚定点: First position <= Last Number
            int target = nums[nums.length - 1];
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return Math.min(nums[start],nums[end]);
    }
}
