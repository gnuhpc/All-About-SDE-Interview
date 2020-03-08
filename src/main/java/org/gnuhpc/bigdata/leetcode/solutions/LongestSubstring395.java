package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;
import scala.Char;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring395 {
    /*
    核心思想：如果某个字符 x 在整个字符串中出现的次数 <k，
    那么 x 不可能出现在最终要求的子串中。
    因此，可以将原字符串截断为：
    x 左侧字符子串 + x + x 右侧字符子串
    因此，问题就被拆分为对左子串、右子串求解这两个子问题。
     */

    public int longestSubstring(String s, int k) {
        if (k < 2)
            return s.length();
        return count(s, k, 0, s.length() - 1);
    }

    public int count(String s, int k, int left, int right) {
        //因为字符出现次数不少于k，因此子串的最短长度为k , 长度小于k的返回0
        if (right - left + 1 < k) {
            return 0;
        }
        int[] num = new int[26]; //统计left--right的字符个数
        for (int i = left; i <= right; i++) {
            num[s.charAt(i) - 'a']++;
        }
        //从字符串left、right开始分别向内部进行推进，如果字符个数小于k则继续向内部移动
        while (right - left + 1 >= k && num[s.charAt(left) - 'a'] < k)
            left++;
        while (right - left + 1 >= k && num[s.charAt(right) - 'a'] < k)
            right--;
        if (right - left + 1 < k)
            return 0;
        //进行分治递归
        for (int i = left; i <= right; i++) {
            //如果剩余的字符串其中有某个字符总数小于k，则从排除该字符并分为两部分子串继续递归
            if (num[s.charAt(i) - 'a'] < k)
                return Math.max(count(s, k, left, i - 1), count(s, k, i + 1, right));
        }
        return right - left + 1;
    }


    @Test
    public void test() {
        System.out.println(longestSubstring("ababbc", 2));

    }
}
