package org.gnuhpc.interview.leetcode.solutions;

public class TrappingRainWater42 {
    // 双指针
    // 本题的要点在于当前位置的储水量，需要比较【左峰-右峰】
    // 再根据短板是谁，决定是哪个指针前移
    // 以其中一个分支为例，leftMost[left] < rightMost[right]
    // 说明当前左节点具有可比性，因为
    // leftMost <= leftMost[left] < rightMost[right] <= rightMost[left]
    // 那么可以确定左节点的储水量，右节点的暂时还确定不了
    public int trap(int[] height) {
        if (height == null || height.length <= 1) return 0;
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int leftMost = 0;
        int rightMost = 0;
        int sum = 0;
        while (left < right) {
            leftMost = Math.max(leftMost, height[left]);
            rightMost = Math.max(rightMost, height[right]);
            if (leftMost < rightMost) {
                sum += leftMost - height[left];
                left++;
            } else {
                sum += rightMost - height[right];
                right--;
            }
        }
        return sum;
    }

}
