package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2019/9/22
 */
public class CharacterReplacement424 {
    /*
    最多替换k次后重复的子串最大长度
    目标就是争取让这个sliding window里面通过替换k次就可以得到一个所有字符都一样的子串，
    那么这道题就转换为求这种sliding window的大小
    替换次数n = sliding window length - 最长出现字母的次数
    当n>k的时候，就算把k个替换名额用光，这个滑动窗口也不会都是一个字符，
    因此当出现length- maxCount>k 的时候，滑动窗口的左边就收紧
    因为求得是最大可能的子串，因此收紧不需要更新max，往后接着算结果就是
     */
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0)
            return 0;
        int left = 0;
        int res = 0;
        int max = 0;
        char[] chs = new char[26];
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            chs[ch - 'A']++;  //最右边的字符出现次数加一
            max = Math.max(max, chs[ch - 'A']);  //更新当前窗口内出现最多的字符次数
            while (right - left + 1 - max > k) {  //当前需要替换的字符数大于k,窗口左边收缩，直到窗口大小收缩到 max + k
                chs[s.charAt(left++) - 'A']--;  //这里只更新最右边的字符次数，不用更新 max
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

}
