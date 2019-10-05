package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;

public class CanWin294 {
    // add by tina 在293的基础上增加了难度，拆解为递归问题

    public boolean canWin(String s) {
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == '+' && s.charAt(i-1) == '+' && !canWin(s.substring(0, i - 1) + "--" + s.substring(i + 1))) {
                return true;
            }
        }
        return false;
    }

    // 优化点memosearch
    private HashMap<String,Boolean> hmap = new HashMap<>();;
    public boolean canWin2(String s) {
        if(hmap.containsKey(s)) return hmap.get(s);
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == '+' && s.charAt(i-1) == '+'){
                String ss = s.substring(0, i - 1) + "--" + s.substring(i + 1);
                if(!canWin(ss)) {
                    hmap.put(ss,false);
                    return true;
                }
                hmap.put(ss,true);
            }
        }
        return false;
    }

}
