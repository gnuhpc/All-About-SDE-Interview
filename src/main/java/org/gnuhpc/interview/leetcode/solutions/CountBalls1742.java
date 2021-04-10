package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class CountBalls1742 {
    public int countBalls(int lowLimit, int highLimit) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int i = lowLimit; i <= highLimit; i++) {
            int num = helper(i);
            int count = map.getOrDefault(num, 0) + 1;
            max = Math.max(max,count);
            map.put(num, count);
        }
        return max;

    }
    public int helper(int num) {
        int res = 0;
        while(num != 0) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }
}
