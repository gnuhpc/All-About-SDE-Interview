package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//DONE
public class CanWin294 {
    /*
      暴力解法
      add by tina 在293的基础上增加了难度，拆解为递归问题
     */
    public boolean canWin(String s) {
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+' &&
                !canWin(s.substring(0, i - 1) + "--" + s.substring(i + 1))) {
                return true;
            }
        }
        return false;
    }

    /*

      Method2: memosearch
     */
    private Map<String, Boolean> hmap = new HashMap<>();

    public boolean canWin2(String s) {
        if (hmap.containsKey(s)) return hmap.get(s);
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                String ss = s.substring(0, i - 1) + "--" + s.substring(i + 1);
                if (!canWin2(ss)) {
                    hmap.put(ss, false);
                    return true;
                }
                hmap.put(ss, true);
            }
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(canWin2("++++"));
    }

}
