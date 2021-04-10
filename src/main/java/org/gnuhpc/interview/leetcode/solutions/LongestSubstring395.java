package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class LongestSubstring395 {
    public int longestSubstring(String s, int k) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int rst = 0;

        for (int i = 1; i <= 26; i++) {//每个循环中，滑动窗口内只能有i个不同字符
            //滑动窗口-双指针
            int left = 0, right = 0;
            int[] cnt = new int[1000];//记录每个字符出现的次数
            //窗口内不同字符数diff_count
            //窗口内大于等于k的字符数count
            int diff_count = 0, count = 0;
            while (right < n) {
                cnt[sc[right]]++;
                if (cnt[sc[right]] == 1) {
                    diff_count++;
                }
                if (cnt[sc[right]] == k) {
                    count++;
                }
                right++;

                while (left < right && diff_count > i) {//窗口中只能有i个字符，超出就要左移
                    if (cnt[sc[left]] == k) {
                        count--;
                    }
                    if (cnt[sc[left]] == 1) {
                        diff_count--;
                    }
                    cnt[sc[left]]--;
                    left++;
                }
                //窗口内出现i个不同字符并且每个字符都大于等于k
                if (diff_count == i && diff_count == count) {
                    rst = Math.max(rst, right - left);
                }
            }
        }
        return rst;
    }


    @Test
    public void test() {
        System.out.println(longestSubstring("ababbc", 2));

    }
}
