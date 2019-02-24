package org.gnuhpc.bigdata.leetcode;

import java.util.*;

public class WordPattern290 {
    public static boolean wordPattern(String pattern, String str) {
        char[] patterns = pattern.toCharArray();
        Map<Character,String> maps = new HashMap<>();
        String[] strs = str.split(" ");

        if(patterns.length != strs.length)
            return false;

        for(int i = 0;i<patterns.length;i++){
            char ch = patterns[i];
            if(maps.containsKey(ch)){
                String value = maps.get(ch);
                if(!value.equals(strs[i]))
                    return false;
            }else{
                if(maps.containsValue(strs[i]))
                    return false;
                maps.put(ch,strs[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba", str = "dog cat cat dog";

        System.out.println(wordPattern2(pattern,str));
    }

    // 2个map，判断包含key的方法，一个map就用containsKey和containsValue
    public static boolean wordPattern2(String pattern, String str) {
        Map<Character,String> mapPS = new HashMap<Character,String>();
        Map<String,Character> mapSP = new HashMap<String,Character>();
        String[] strArr = str.split(" ");
        if(strArr.length != pattern.length()) return false;
        for(int i = 0; i< strArr.length; i++){
            String s = strArr[i];
            Character c = (Character) pattern.charAt(i);
            if(mapPS.containsKey(c) && (!s.equals(mapPS.get(c)))) return false;
            else if(mapSP.containsKey(s) && (c != mapSP.get(s))) return false;
            else {
                mapPS.put(c,s);
                mapSP.put(s,c);
            }
        }
        return true;


    }
}
