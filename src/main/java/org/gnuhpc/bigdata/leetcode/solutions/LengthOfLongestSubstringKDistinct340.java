package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class LengthOfLongestSubstringKDistinct340 {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        Map<Character, List<Integer>> cache = new HashMap<>();
        char[] arr = s.toCharArray();

        int left = 0, right = 0;
        int res = 0;
        while (right < arr.length) {
            char c = arr[right];
            if (cache.size() < k) {
                List<Integer> list = cache.get(c);
                if (list == null) list = new ArrayList<>();
                list.add(right);
                cache.put(c, list);
            }
            else if (cache.size() == k) {
                if (cache.containsKey(c)) {
                    cache.get(c).add(right);
                }
                else {
                    res = Math.max(res, right - left);
                    List<Integer> list = new ArrayList<>();
                    list.add(right);
                    cache.put(c, list);

                    while (cache.size() > k) {
                        List<Integer> l = cache.get(arr[left]);
                        l.remove(0);
                        if (l.size() == 0) cache.remove(arr[left]);
                        left++;
                    }
                }
            }
            right++;
        }

        return Math.max(res, right - left);
    }

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstringKDistinct("aba", 1));
    }

    //add by tina,只需要记录次数，不需要记录list
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        if (s == null || s.isEmpty() || k < 1) return 0;
        if (s.length() <= k) return s.length();

        int result = k;
        int pre = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);

            while (map.size() > k) {
                char p = s.charAt(pre++);
                int count = map.get(p) - 1;
                if (count == 0) {
                    map.remove(p);
                }
                else {
                    map.put(p, count);
                }
            }
            result = Math.max(result, i - pre + 1);
        }
        return result;
    }
}
