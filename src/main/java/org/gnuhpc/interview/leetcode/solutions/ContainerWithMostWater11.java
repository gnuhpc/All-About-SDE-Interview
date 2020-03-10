package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Arrays;

public class ContainerWithMostWater11 {
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 4, 3};
        System.out.println(findContainer(nums));

    }

    /*
    Method: 双指针,因为要求最大的面积,所以双指针是从一头一尾进行
     */
    public int findContainer(int[] height) {
        int i = 0, j = height.length - 1;
        int max = 0;

        while (i < j) {
            int area = (Math.min(height[i], height[j])) * (j - i);
            if (area > max) {
                max = area;
            } else {
                if (height[i] <= height[j]) {
                    i++;
                } else if (height[i] > height[j]) {
                    j--;
                }
            }

        }

        return max;
    }
}
