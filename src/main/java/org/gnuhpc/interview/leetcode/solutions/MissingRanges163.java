package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MissingRanges163 {
    // 首位区间的处理，需要与面试官进一步沟通
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            res.add(getInterval(lower, upper));
            return res;
        }

        String begin = getInterval(lower, (long) nums[0] - 1);
        if (!begin.equals("")) res.add(begin);

        for (int i = 1; i < nums.length; i++) {
            String mid = getInterval((long) nums[i - 1] + 1, (long) nums[i] - 1);
            if (!mid.equals("")) res.add(mid);
        }
        String end = getInterval((long) nums[nums.length - 1] + 1, upper);
        if (!end.equals("")) res.add(end);
        return res;
    }

    private String getInterval(long start, long end) {
        if (start > end) return "";
        else if (start == end) return String.valueOf(start);
        else {
            return start + "->" + end;
        }
    }

    @Test
    public void test() {
        int[] nums = {0, 1, 3, 50, 75};
        System.out.println(findMissingRanges(nums, 0, 99));
    }

}

