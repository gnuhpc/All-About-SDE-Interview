package org.gnuhpc.interview.leetcode.solutions;

import com.google.inject.internal.cglib.proxy.$ProxyRefDispatcher;
import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/16
 */
public class FindMissingRanges163 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            if (lower == upper) res.add("" + lower);
            else {
                res.add(mkRange(lower, upper));
            }
            return res;
        }

        if (nums[0] > (long) (lower + 1)) {
            res.add(mkRange(lower, nums[0]));
        } else if (nums[0] == (long) (lower + 1)) {
            res.add("" + lower);
        }

        for (int i = 0; i < nums.length; i++) {
            long start = (long) nums[i] + 1;
            if (i == nums.length - 1) {
                long end = upper;
                if (start < end) res.add(mkRange(start, end));
                else if (start == end) res.add("" + start);
                break;
            } else {
                long end = (long) nums[i + 1] - 1;
                if (start < end) res.add(mkRange(start, end));
                else if (start == end) res.add("" + start);
            }
        }


        return res;
    }

    private String mkRange(long start, long end) {
        return start + "->" + end;
    }

    @Test
    public void test() {
        System.out.println(findMissingRanges(new int[]{2147483647}, 0, 2147483647));
    }


}
