package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram242 {
    public static void main(String[] args) {
        String s = "anagrm", t = "nagaram";
        System.out.println(isAnagram(s,t));


    }

    public static boolean isAnagram(String s, String t) {
        Map<Character,Integer> charMap = new HashMap<>();

        for (char c : s.toCharArray()){
            if (charMap.containsKey(c)) {
                charMap.put(c, charMap.get(c) + 1);
            } else {
                charMap.put(c, 1);
            }
        }

        for (char c: t.toCharArray()){
            if (charMap.containsKey(c)){
                charMap.put(c,charMap.get(c)-1);
            } else {
                return false;
            }
        }

        return charMap.values().stream().filter(i->i!=0).count()==0;

    }
}
