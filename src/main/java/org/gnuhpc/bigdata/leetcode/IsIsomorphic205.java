package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IsIsomorphic205 {
    /*
    Hash 表方法去检查一对一的关系
     */
    public static boolean isIsomorphic(String s, String t) {
        Map<Character,Character> charMap = new HashMap<>();

        if ( s==null ||  t==null ||s.length()!=t.length() ){
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if(!charMap.containsKey(s.charAt(i))){
                final int ii = i;
                //如果已经有映射了说明有问题！
                if(charMap.values().stream().anyMatch(c -> c == t.charAt(ii))){
                    return false;
                }
                charMap.put(s.charAt(i),t.charAt(i));
            } else {
                Character c = charMap.get(s.charAt(i));
                if (c != t.charAt(i)){
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s="foo", t="bar";
        String s1="ab", t1="aa";

        System.out.println(isIsomorphic(s,t));
        System.out.println(isIsomorphic(s1,t1));
    }

    public boolean isIsomorphic2(String s, String t) {
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

    public boolean isIsomorphic3(String s, String t) {
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

}
