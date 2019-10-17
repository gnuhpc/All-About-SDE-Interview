package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

public class IsStrobogrammatic246 {
    public boolean isStrobogrammatic(String num) {
        Map<Character,Character> mapping = new HashMap<>();
        mapping.put('6','9');
        mapping.put('9','6');
        mapping.put('1','1');
        mapping.put('0','0');
        mapping.put('8','8');
        for(int i=0;i<num.length();i++) {
            if(mapping.get(num.charAt(i))==null) return false;
            if(num.charAt(num.length()-1-i)!= mapping.get(num.charAt(i))) return false;
        }
        return true;
    }

}
