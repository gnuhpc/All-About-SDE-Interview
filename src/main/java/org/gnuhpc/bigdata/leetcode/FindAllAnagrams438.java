package org.gnuhpc.bigdata.leetcode;

import com.google.inject.internal.cglib.proxy.$ProxyRefDispatcher;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  Anagrams指几个string有相同的字符，但不同的字符顺序。
 */

public class FindAllAnagrams438 {
    @Test
    public void test(){
        String str1 = "cbaebabacd";
        String p1 = "abc";

        String str2 = "aaab";
        String p2 = "ba";

//        System.out.println(findAnagrams(str1,p1));
        System.out.println();
        System.out.println(findAnagrams2(str1, p1));
    }

    /**
     * 双指针
     * O(m*n)
     *
     * @param s
     * @param p
     * @return
     */

    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int[] map = new int[128]; //由于都是小写字母，因此直接用26个长度的数组代替原来的HashMap
        int left = 0, right = 0, total = p.length(); //用total检测窗口中是否已经涵盖了p中的字符
        for (char ch : p.toCharArray()) {
            map[ch]++;
        }

        char[] chS = s.toCharArray();
        while (right < s.length()) {
            if (map[chS[right++]]-- > 0) {
                total--;
            }

            while (total == 0) {
                if (right - left == p.length()) {
                    res.add(left);
                }
                if (map[chS[left++]]++ == 0) {
                    total++;
                }
            }
        }
        return res;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int[] cntS = new int[256];
        int[] cntP = new int[256];
        char[] charS = s.toCharArray();
        char[] charP = p.toCharArray();
        int lenS = s.length();
        int lenP = p.length();

        List<Integer> res = new ArrayList<>();

        if (lenS < lenP) return res;

        for (int i = 0; i < lenP; i++) {
            cntS[charS[i]]++;
            cntP[charP[i]]++;
        }

        if (Arrays.equals(cntS, cntP)) {
            res.add(0);
        }

        for (int i = lenP; i < lenS; i++) {
            cntS[charS[i]]++;
            cntS[charS[i - lenP]]--;

            if (Arrays.equals(cntS, cntP)) {
                res.add(i - lenP + 1);
            }
        }

        return res;
    }
}

