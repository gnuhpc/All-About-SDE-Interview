package org.gnuhpc.bigdata.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  Anagrams指几个string有相同的字符，但不同的字符顺序。
 */

public class FindAllAnagrams438 {
    public static void main(String[] args) {
        String str1 = "cbaebabacd";
        String p1 = "abc";

        String str2 = "abab";
        String p2 = "ba";

        System.out.println(findAllAnagrams(str1,p1));
        System.out.println();
        System.out.println(findAllAnagrams(str2,p2));
    }

    private static List<Integer> findAllAnagrams(String str, String p) {
        int l=0, r=-1;
        char[] strArray = str.toCharArray();
        List<Integer> result = new ArrayList<>();
        Map<Character, Long> pMap = buildPMap(p);

        while(r + 1 < str.length()){
            //尚未匹配完成
            if(!isAllZero(pMap.values())){
                r++;
                Long mapValue = pMap.get(strArray[r]);
                if (mapValue !=null) {
                    if(mapValue>0){
                        pMap.put(strArray[r], mapValue - 1);
                    } else { //Extra valid char appears
                        pMap.put(strArray[l],pMap.get(strArray[l])+1);
                        l++;
                        r--;
                    }
                } else { // 匹配异常
                    pMap = buildPMap(p);
                    l = r + 1;
                }
            } else { //匹配完成
                result.add(l);
                pMap.put(strArray[l],pMap.get(strArray[l])+1);
                l++;
            }

        }

        if (isAllZero(pMap.values())){
            result.add(l);
        }
        return result;
    }

    private static Map<Character, Long> buildPMap(String p){
        return p.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }
    private static boolean isAllZero(Collection<Long> values){
        return values.stream().filter(l->l!=0).count()==0;
    }


    /**
     * 优化后，O(N)
     思路: 把字符串的问题转换成数组处理。
     这个题目是求给定的pattern的anagram,在给定的字符串中出现的位置;
     需要借助两个数组, 一个是给定的pattern数组，用来存pattern中各个字符的出现次数。
     另一个长度等同于pattern的滑动窗口，当前window下的各个字符的出现次数。
     当window遍历给定的字符s的时候，做一下几件事情:
     1. window中前一个坐标对应的字符的次数-1； window中新加进来的字符的次数+1.
     然后比较当前window下的anagram跟pattern 是否相同。
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return res;
        if (s.length() < p.length()) return res;
        int[] pattern = new int[26];
        int[] window = new int[26];
        int pLen = p.length();

        for (int i = 0; i < pLen; i++) {
            int pIdx = p.charAt(i) - 'a';
            int sIdx = s.charAt(i) - 'a';
            pattern[pIdx]++;
            window[sIdx]++;
        }
        if (isAnagram(window, pattern)) res.add(0);
        for (int i = pLen; i < s.length(); i++) {
            int preIdx = i - pLen;
            window[s.charAt(preIdx) - 'a']--;
            window[s.charAt(i) - 'a']++;
            if (isAnagram(window, pattern)) res.add(i - pLen + 1);
        }
        return res;
    }
    public boolean isAnagram(int[] window, int[] pattern) {
        for (int i = 0; i < pattern.length; i++) {
            if(window[i] != pattern[i]) return false;
        }
        return true;
    }


    /**
     * 直观解法
     * O(m*n)
     * @param s
     * @param p
     * @return
     */

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        //<all char,freq in t>
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : s.toCharArray()) map.put(c, 0);
        for(char c : p.toCharArray()){
            if(map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                return res;
        }
        int start = 0, end = 0;
        int counter = p.length();
        while(end < s.length()){
            char cur = s.charAt(end);
            if(map.get(cur) > 0) counter--;
            map.put(cur, map.get(cur) - 1);
            while (counter == 0){
                if(end - start + 1 == p.length()) res.add(start);
                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) + 1);
                if(map.get(c2) > 0) counter++;
                start++;
            }
            end++;
        }
        return res;
    }


}

