package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class IsOneEditDistance161 {
    // add by tina
    /**
     * 1. 两个字符串的长度之差大于1，直接返回False。
     *
     * 2. 两个字符串的长度之差等于1，长的那个字符串去掉一个字符，剩下的应该和短的字符串相同。
     *
     * 3. 两个字符串的长度之差等于0，两个字符串对应位置的字符只能有一处不同。
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        // Ensure that s is shorter than t.
        if (ns > nt)
            return isOneEditDistance(t, s);

        // The strings are NOT one edit away distance
        // if the length diff is more than 1.
        if (nt - ns > 1)
            return false;

        for (int i = 0; i < ns; i++){
            if (s.charAt(i) != t.charAt(i)){
                // if strings have the same length
                if (ns == nt)
                    return s.substring(i + 1).equals(t.substring(i + 1));
                    // if strings have different lengths
                else
                    return s.substring(i).equals(t.substring(i + 1));
            }
        }

        // If there is no diffs on ns distance
        // the strings are one edit away only if
        // t has one more character.
        return (ns + 1 == nt);
    }

    Map<String, Integer> cache = new HashMap<>();

    public boolean isOneEditDistance2(String s, String t) {
        return distance(s, t) == 1;
    }

    private int distance(String s, String t) {
        if (s.length() > t.length()) {
            String temp = s;
            s = t;
            t = temp;
        }
        String key = s + "-" + t;
        if (cache.containsKey(key)) return cache.get(key);
        if (Math.abs(s.length() - t.length()) > 1) {
            cache.put(key, 2);
            return 2;
        }
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(j)) {
                return distance(s.substring(i + 1), t.substring(i + 1));
            }
            else {
                return Math.min(1 + distance(s, t.substring(i + 1)),
                                1 + distance(s.substring(i + 1), t.substring(i + 1)));
            }
        }

        cache.put(key, s.equals(t) ? 0 : 1);

        return cache.get(key);
    }


    @Test
    public void test() {
        System.out.println(isOneEditDistance2("cb", "ab"));
    }


}
