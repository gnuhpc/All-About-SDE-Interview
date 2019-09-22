package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsSubsequence392 {
    /*
    Method1 : 递归
     */
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;

        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(0)){
                return isSubsequence(s.substring(1),t.substring(i+1));
            }
        }

        return false;
    }

    /*
    Method2: 双指针
     */
    public boolean isSubsequence2(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) return true;
            }
            indexT++;
        }
        return false;
    }

    /**
     * Follow-up
     * If we check each sk in this way, then it would be O(kn) time where k is the number of s and t is the length of t.
     * This is inefficient.
     * Since there is a lot of s, it would be reasonable to preprocess t to generate something that is easy to search for if a character of s is in t.
     * Sounds like a HashMap, which is super suitable for search for existing stuff.
     */
    public boolean isSubsequence3(String s, String t) {
        if (s == null || t == null) return false;

        Map<Character, List<Integer>> map = new HashMap<>(); //

        //preprocess t, e.g. t = "abcdac"， s = "adc"
        /*
        memo:
        a: 0,4
        b: 1
        c: 2,5
        d: 3
         */
        for (int i = 0; i < t.length(); i++) {
            char curr = t.charAt(i);
            if (!map.containsKey(curr)) {
                map.put(curr, new ArrayList<>());
            }
            map.get(curr).add(i);
        }

        int prev = -1;  //index of previous character, 找到的那个字母在s中的index+1
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.get(c) == null)  {
                return false;
            } else {
                List<Integer> list = map.get(c);
                prev = binarySearch(prev, list, 0, list.size() - 1);
                if (prev == -1) {
                    return false;
                }
                prev++;
            }
        }

        return true;
    }

    //通过二分法找到index后边还有没有idx
    private int binarySearch(int index, List<Integer> list, int start, int end) {
        if (index > list.get(list.size()-1)) return -1;

        if (index == -1) return list.get(0); //如果index是初始值，取list中的第一个

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < index) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (list.get(start) >= index) return list.get(start);
        else
            return list.get(end);
    }



    @Test
    public void test(){
        System.out.println(isSubsequence("abc", "ahbgdc"));
        System.out.println(isSubsequence("axc", "ahbgdc"));
        System.out.println(isSubsequence3("aae", "ahabebgdac"));
    }
}
