package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LengthOfLongestSubstringTwoDistinct159 {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] map = new int[128];
        int ret = Integer.MIN_VALUE;
        int start = 0, end = 0;
        int counter = 0;

        while (end < s.length()) {
            char c = s.charAt(end++);
            map[c]++;
            if (map[c] == 1) {
                counter++;
            }

            while (counter > 2) {
                char cTemp = s.charAt(start);
                map[cTemp]--;
                if (map[cTemp] == 0) {
                    counter--;
                }
                start++;
            }

            ret = Math.max(ret, end - start);
        }

        return ret;
    }


    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));
    }

}
