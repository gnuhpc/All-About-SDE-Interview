package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

public class IsScramble87 {
    public boolean isScramble(String s1, String s2) {
        Map<String, Integer> memoization = new HashMap<>();
        return isScrambleRecursion(s1, s2, memoization);
    }

    public boolean isScrambleRecursion(String s1, String s2, Map<String, Integer> memoization) {
        //判断之前是否已经有了结果
        int ret = memoization.getOrDefault(s1 + "#" + s2, -1);
        if (ret == 1) {
            return true;
        } else if (ret == 0) {
            return false;
        }
        if (s1.length() != s2.length()) {
            memoization.put(s1 + "#" + s2, 0);
            return false;
        }
        if (s1.equals(s2)) {
            memoization.put(s1 + "#" + s2, 1);
            return true;
        }

        int[] letters = new int[26]; //统计词频，不一致的也返回，返回前先记录
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++)
            if (letters[i] != 0) {
                memoization.put(s1 + "#" + s2, 0);
                return false;
            }

        //遍历每个切割位置
        for (int i = 1; i < s1.length(); i++) {
            //对应情况 1 ，判断 S1 的子树能否变为 S2 相应部分
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                memoization.put(s1 + "#" + s2, 1);
                return true;
            }
            //对应情况 2 ，S1 两个子树先进行了交换，然后判断 S1 的子树能否变为 S2 相应部分
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                memoization.put(s1 + "#" + s2, 1);
                return true;
            }
        }
        memoization.put(s1 + "#" + s2, 0);
        return false;
    }
}
