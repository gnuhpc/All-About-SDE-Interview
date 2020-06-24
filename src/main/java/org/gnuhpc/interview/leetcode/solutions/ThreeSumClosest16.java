package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

public class ThreeSumClosest16 {
    @Test
    public void test() {
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;

        System.out.println(threeSumClosest(nums, target));
    }

    public int threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int result = 0;
        assert (nums != null && nums.length >= 3);
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int L = i + 1;
            int R = nums.length - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                int diff = Math.abs(sum - target);

                // 如果diff为0， 说明three sum等于target，则可以直接返回了，最接近的就是target本身
                if (diff == 0) return target;

                //如果diff小于min,则表示这次为潜在答案，先记录
                if (diff < min) {
                    min = diff;
                    result = sum;
                }

                //移动指针
                if (sum <= target) {
                    L++;
                } else {
                    R--;
                }
            }
        }

        return result;
    }
}
