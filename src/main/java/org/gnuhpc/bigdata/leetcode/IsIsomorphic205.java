package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IsIsomorphic205 {
    /*
    Hash 表方法去检查一对一的关系
     */

    @Test
    public void test() {
        String s="foo", t="bar";
        String s1="ab", t1="aa";

        System.out.println(isIsomorphic(s,t));
        System.out.println(isIsomorphic(s1,t1));
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) return false;
            } else {
                if (map.containsValue(t.charAt(i))) return false;
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        int[] stmap = new int[256];
        int[] tsmap = new int[256];
        Arrays.fill(stmap, -1);
        Arrays.fill(tsmap, -1);
        for (int i = 0; i < s.length(); i++) {
            if (stmap[s.charAt(i)] != tsmap[t.charAt(i)]) return false;
            stmap[s.charAt(i)] = i;
            tsmap[t.charAt(i)] = i;
        }
        return true;
    }

    // add by tina，比方法1快，可见map.containsValue()的判断是很耗时的
    public boolean isIsomorphic3(String s, String t) {
        if(s == null || t == null) return false;
        if(s.length() == 0 && t.length() == 0) return true;
        HashMap<Character,Character> hmap = new HashMap<>();
        HashMap<Character,Character> hmapt = new HashMap<>();
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(hmap.containsKey(c)){
                if(t.charAt(i) == hmap.get(c)) continue;
                else return false;
            }else{
                if(hmapt.containsKey(t.charAt(i))) return false;
                hmap.put(c,t.charAt(i));
                hmapt.put(t.charAt(i),c);
            }
        }
        return true;

    }

}
