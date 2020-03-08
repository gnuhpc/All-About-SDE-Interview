package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Copyright gnuhpc 2020/2/12
 */
public class RandomPickIndex398 {
    private int[] nums;

    public RandomPickIndex398(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                list.add(i);
            }
        }
        Random rand = new Random();
        //假设list.size() = 10; 则 rand.next(list.size()) 生成 [0, 10)
        return list.get(rand.nextInt(list.size()));
    }
}
