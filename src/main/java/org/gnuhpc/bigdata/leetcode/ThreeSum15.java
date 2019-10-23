package org.gnuhpc.bigdata.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum15 {
    public void test() {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        int[] nums2 = new int[] {0,0,0,0};

        threeSum(nums);
        threeSum(nums2);
        System.out.println();
    }


    /**
     *
     * 通过减治的思想，我们可把三个数求和的问题，减治为对于数组中不重复的元素nums[i],求两个数的和等于-nums[i]的过程。
     * 这个问题比较复杂的一点是，还要处理重复的数据。为了简化我们的操作，我们先对数组进行预排序。
     * 经历了预排序，我们判断一个元素是否重复，只需要比较它和之前位置的元素是否相等就可以了。
     * 排序之后，对于求两个数和的问题，可以通过low和high两个指针从两边查找，也简化了操作时间。
     * @param nums : Give an array numbers of n integer
     * @return : Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return results;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // skip duplicate triples with the same first numebr
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1, right = nums.length - 1;
            int target = 0 - nums[i];

            twoSum(nums, left, right, target, results);
        }

        return results;
    }

    public void twoSum(int[] nums,
                       int left,
                       int right,
                       int target,
                       List<List<Integer>> results) {
        while (left < right) {
            if (nums[left] + nums[right] == target) {
                ArrayList<Integer> triple = new ArrayList<>();
                triple.add(-target);
                triple.add(nums[left]);
                triple.add(nums[right]);
                results.add(triple);

                left++;
                right--;
                // skip duplicate pairs with the same left TODO
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                // skip duplicate pairs with the same right
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
    }
}
