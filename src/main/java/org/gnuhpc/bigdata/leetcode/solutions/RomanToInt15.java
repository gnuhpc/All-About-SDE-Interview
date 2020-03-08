package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt15 {
    static final Map<Character, Integer> map = new HashMap<Character, Integer>();

    static {
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
    }

    public int romanToInt(String s) {
        int res = 0;
        if (s.length() == 0) return res;
        for (int i = 0; i < s.length() - 1; i++) {
            int cur = map.get(s.charAt(i));
            int nex = map.get(s.charAt(i + 1));
            if (cur < nex) {
                res -= cur;
            }
            else {
                res += cur;
            }
        }
        return res + map.get(s.charAt(s.length() - 1));
    }


}
