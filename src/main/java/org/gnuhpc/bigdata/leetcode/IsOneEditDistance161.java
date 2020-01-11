package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class IsOneEditDistance161 {
    // add by tina

    /**
     * 1. 两个字符串的长度之差大于1，直接返回False。
     * <p>
     * 2. 两个字符串的长度之差等于1，长的那个字符串去掉一个字符，剩下的应该和短的字符串相同。
     * <p>
     * 3. 两个字符串的长度之差等于0，两个字符串对应位置的字符只能有一处不同。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();
        char[] sChs = s.toCharArray();
        char[] tChs = t.toCharArray();

        // Ensure that s is shorter than t.
        if (ns > nt)
            return isOneEditDistance(t, s);

        int diff = nt - ns;

        // The strings are NOT one edit away distance
        // if the length diff is more than 1.
        if (diff > 1)
            return false;

        else if (diff == 1) {
            for (int i = 0; i < ns; i++) {
                if (sChs[i] != tChs[i]) {
                    return s.substring(i).equals(t.substring(i));
                }
            }

            return true;
        }

        else { //diff == 0
            int cnt = 0;
            for (int i = 0; i < ns; i++) {
                if (sChs[i] != tChs[i]) {
                    cnt++;
                }
            }

            return (cnt == 1);
        }

    }

    // Str1_Str2 -- their distances 通用方法
    Map<String, Integer> cache = new HashMap<>();

    public boolean isOneEditDistance2(String s, String t) {
        return distance(s, t) == 1;
    }

    private int distance(String s, String t) {
        //s must be shorter than t
        if (s.length() > t.length()) {
            String temp = s;
            s = t;
            t = temp;
        }

        String key = s + "-" + t;
        if (cache.containsKey(key)) return cache.get(key);

        if (s.isEmpty()) {
            cache.put(key, t.isEmpty() ? 0 : 1);
            return cache.get(key);
        }

        int lengthDiff = t.length() - s.length();
        if (lengthDiff > 1) {
            cache.put(key, lengthDiff);//return a distance greater than 2 is already enough for this problem
            return lengthDiff;
        } else if(lengthDiff == 1){
            if (s.charAt(0) == t.charAt(0)) {
                return distance(s.substring(1), t.substring(1));
            } else{
                return 1+distance(s,t.substring(1));//delete the first char of t and continue
            }
        } else if(lengthDiff == 0){
            if (s.charAt(0) == t.charAt(0)) {
                return distance(s.substring(1), t.substring(1));
            } else{
                return 1+distance(s.substring(1), t.substring(1));
            }
        } else{ //Never reach because s is shorted than t
            return -1;
        }
    }

    @Test
    public void test() {
        System.out.println(isOneEditDistance("cbb", "ab"));
    }


}
