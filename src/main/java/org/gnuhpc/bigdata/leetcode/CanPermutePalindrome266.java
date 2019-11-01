package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2019/10/18
 */
public class CanPermutePalindrome266 {
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128]; //ASCII码128个

        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            // add by tina,不需要加!所有s中的串都在0-128内
            map[arr[i]]++;
            //map[arr[i] - '!']++; //为什么是感叹号? 因为是ASCII码的第一个非空格的可见字符.
        }

        int res = 0;

        for (int n : map) {
            if (n % 2 == 1) res++;
        }

        if (res <= 1) return true;
        else return false;
    }

    // add by tina
    public boolean canPermutePalindrome2(String s) {
        if(s == null) return false;
        if(s.isEmpty()) return true;

        Map<Character,Integer> map = new HashMap<>();

        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }

        int even = 0;
        for(Map.Entry<Character,Integer> en:map.entrySet()){

            if(en.getValue()%2 == 1) even++;
            if(even>1) return false;
        }

        return true;

    }
}
