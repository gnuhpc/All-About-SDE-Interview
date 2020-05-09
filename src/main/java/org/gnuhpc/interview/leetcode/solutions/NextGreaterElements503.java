package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2020/5/9
 */
public class NextGreaterElements503 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> s = new LinkedList<>();
        int i = 0;
        while (i < 2 * nums.length) {
            if (s.isEmpty() || nums[i % n] <= nums[s.peek() % n]) {//可能会更小，因此往后走
                s.push(i++);//如果只关注数值可以直接存入数值，如果关注idx之间的距离，也就是和最值的距离，则存入idx
            } else {
                //另外注意在处理最值的时候是不动i的
                int tp = s.pop();  // store the top index
                res[tp % n] = nums[i % n];
            }
        }

        return res;
    }

    @Test
    public void test() {
        Utils.printArray(nextGreaterElements(new int[]{1, 2, 1}));
    }
}
