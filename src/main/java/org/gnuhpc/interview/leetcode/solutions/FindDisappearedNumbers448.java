package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindDisappearedNumbers448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            //当前数字本就出现在理应的位置上，跳过，不用换。当前数字理应出现的位置上，已经存在当前数字，跳过，不用换。
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) swap(nums, i, nums[i] - 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) ans.add(i + 1);
        }
        return ans;
    }

    private void swap(int[] nums, int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }

    @Test
    public void test() {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
