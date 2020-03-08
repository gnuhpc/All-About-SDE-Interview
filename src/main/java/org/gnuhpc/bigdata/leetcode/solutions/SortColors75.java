package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.Arrays;

public class SortColors75 {
    public void sortColors(int[] nums) {
        int[] counts = new int[3];

        for (int i = 0; i < nums.length; i++) {
            counts[nums[i]]++;
        }

        int k = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = k; j < counts[i] + k; j++) {
                nums[j] = i;
            }
            k += counts[i];
        }
    }

    //        return new int[] { lt, gt };
    /*  Method2: 三路快排的partition, 这个案例中pivot为1
        lt位置的左边都是小于pivot的
        lt到i都是等于pivot的
        i到gt都是不知道的
        gt的右边的都是大于pivot的

        a[l,lt-1] < pivot
        a[lt, i-1] = pivot
        a[i,gt] = unseen
        a[gt+1, r] > pivot
     */
    public void sortColors2(int[] nums) {
        if (nums == null) return;
        if (nums.length <= 1) return;
        int n = nums.length;
        int i = 0, lt = 0, gt = n - 1;
        int pivot = 1;
        while (i <= gt) {
            if (nums[i] < pivot)
                swap(i++, lt++, nums);//i++的原因是：换过来的数一定小于1。
            else if (nums[i] > pivot)
                swap(i, gt--, nums); //不i++的原因是：换过来的数有可能比1大。
            else
                i++;
        }
    }

    private void swap(int i, int j, int[] nums) {
        if (i == j)
            return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test() {
        int[] nums = new int[]{0, 2, 1, 2, 1, 0, 2};
        sortColors(nums);

        int[] nums2 = new int[]{2, 0, 2, 1, 1, 0};
        sortColors2(nums2);
        Arrays.stream(nums).forEach(System.out::print);
        System.out.println();
        Arrays.stream(nums2).forEach(System.out::print);
    }

    // add by tina, 双指针思想
    public void sortColors3(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) return;
        // nums[0...red) == 0,nums(blue..length-1] == 2
        int red = 0;
        int blue = nums.length - 1;
        for (int cur = 0; cur <= blue; cur++) {
            if (nums[cur] == 0) {
                swap(nums, cur, red);
                red++;
            }
            else if (nums[cur] == 2) {
                swap(nums, cur, blue);
                blue--;
                cur--;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = 0;
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
