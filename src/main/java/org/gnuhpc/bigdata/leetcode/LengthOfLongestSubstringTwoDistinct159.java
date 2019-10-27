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

    //add by tina map中仅仅只需要累计次数即可
    public int lengthOfLongestSubstringTwoDistinct2(String s) {
        int max=0;
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        int start=0;

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c,1);
            }

            if(map.size()>2){
                max = Math.max(max, i-start);

                while(map.size()>2){
                    char t = s.charAt(start);
                    int count = map.get(t);
                    if(count>1){
                        map.put(t, count-1);
                    }else{
                        map.remove(t);
                    }
                    start++;
                }
            }
        }

        //这个情况容易考虑漏，就是start后没有重复字符的情况
        max = Math.max(max, s.length()-start);

        return max;
    }

}
