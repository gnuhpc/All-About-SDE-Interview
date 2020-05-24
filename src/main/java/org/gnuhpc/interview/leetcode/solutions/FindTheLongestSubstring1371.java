package org.gnuhpc.interview.leetcode.solutions;

import com.google.inject.internal.cglib.proxy.$Factory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/5/20
 */

/* TODO 前缀和+状态压缩
https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/solution/mei-ge-yuan-yin-bao-han-ou-shu-ci-de-zui-chang-z-2/
https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/solution/zhuang-tai-ya-suo-ji-lu-yuan-yin-zi-mu-chu-xian-qi/
 */
public class FindTheLongestSubstring1371 {
    public int findTheLongestSubstring(String s) {
        int length = s.length();
        String VOW = "aeiou";
        Map<Integer, Integer> index = new HashMap<>();//存储对应状态出现的最早位置即可
        index.put(0, -1);
        int max = 0;
        int state = 0;

        for (int i = 0; i < length; i++) {
            int p = VOW.indexOf(s.charAt(i));
            if (p != -1) {
                state ^= 1 << p;
            }
            index.putIfAbsent(state, i);
            max = Math.max(max, i - index.get(state));
        }

        return max;
    }

    @Test
    public void test() {
        System.out.println(findTheLongestSubstring("leetcodeisgreat"));
    }
}
