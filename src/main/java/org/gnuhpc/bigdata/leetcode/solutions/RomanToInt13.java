package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashMap;

public class RomanToInt13 {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<String, Integer> hmap = new HashMap<>();

        hmap.put("IV", 4);
        hmap.put("IX", 9);
        hmap.put("XL", 40);
        hmap.put("XC", 90);
        hmap.put("CD", 400);
        hmap.put("CM", 900);

        hmap.put("I", 1);
        hmap.put("V", 5);
        hmap.put("X", 10);
        hmap.put("L", 50);
        hmap.put("C", 100);
        hmap.put("D", 500);
        hmap.put("M", 1000);

        int res = 0;
        int n = s.length();
        if (n == 1) return hmap.get(s);

        for (int i = 0; i < n - 1; i++) {
            String ss = s.substring(i, i + 2);
            System.out.println("ss=" + ss);
            if (hmap.containsKey(ss)) {
                res += Integer.valueOf(hmap.get(ss));
                i++;
            }
            else res += hmap.get(String.valueOf(s.charAt(i)));
            System.out.println("res" + res);
        }

        if (!hmap.containsKey(s.substring(n - 2, n))) res += hmap.get(String.valueOf(s.charAt(n - 1)));

        return res;
    }

}
