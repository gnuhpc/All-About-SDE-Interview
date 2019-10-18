package org.gnuhpc.bigdata.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LengthOfLongestSubstringTwoDistinct159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, List<Integer>> cache = new HashMap<>();
        char[] arr = s.toCharArray();

        int i = 0 , j = 0;
        int res = 0;
        for(;j < arr.length;j++) {
            char c = arr[j];
            if (cache.size()<2) {
                List<Integer> list = cache.get(c);
                if (list == null) list = new ArrayList<>();
                list.add(j);
                cache.put(c,list);
            } else if (cache.size() == 2){
                if (cache.containsKey(c)){
                    cache.get(c).add(j);
                } else{
                    res = Math.max(res, j-i);
                    List<Integer> list = new ArrayList<>();
                    list.add(j);
                    cache.put(c,list);

                    while (cache.size()>2){
                        List<Integer> l = cache.get(arr[i]);
                        l.remove(0);
                        if (l.size() == 0) cache.remove(arr[i]);
                        i++;
                    }
                }
            }
        }

        return Math.max(res,j-i);
    }
}
