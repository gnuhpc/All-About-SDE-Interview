package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//same with the LC 392 follow up
public class NumMatchingSubseq792 {
    /*
    Method1: idx hash map + binary search
     */
    Map<Character, List<Integer>> map = new HashMap<>();
    Map<String, Boolean> cache = new HashMap<>(); //优化点

    public int numMatchingSubseq(String S, String[] words) {
        int res = 0;
        constructMap(map, S);

        for (String word : words) {
            if (isSubseqence(word)) {
                res++;
                cache.put(word,true);
            } else {
                cache.put(word,false);
            }
        }

        return res;
    }

    private boolean isSubseqence(String word) {
        if (cache.containsKey(word)) return cache.get(word);
        int prev = -1;
        char[] arr = word.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (map.get(arr[i]) == null) return false;
            else {
                prev = search(prev, map.get(arr[i]));
                if (prev == -1) return false;
                prev++;//注意要往前走,因为是下一个
            }
        }

        return true;
    }

    //To find out the next idx contains the char.
    //Return -1 when there is no such an element
    private int search(int prev, List<Integer> idxs) {
        if (prev > idxs.get(idxs.size() - 1)) {
            return -1;
        }

        if (prev < idxs.get(0)) {
            return idxs.get(0);
        }

        int start = 0, end = idxs.size() - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;

            if (idxs.get(mid) > prev) end = mid;
            else {
                start = mid;
            }

        }

        if (idxs.get(start) >= prev) return idxs.get(start);
        else
            return idxs.get(end);
    }

    private void constructMap(Map<Character, List<Integer>> map, String s) {
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);//TODO 如果是null就添加
        }
    }

    /*
    Method2: 暴力解 （实质更快）
     */
    public int numMatchingSubseq2(String S, String[] words) {
        Map<String, Boolean> cache = new HashMap<>();
        int index = 0, count = 0;
        boolean sub;
        for (String word : words) {
            if (cache.containsKey(word)) {
                count += cache.get(word)?1:0;
            } else {
                index = -1;
                sub = true;
                for (int i = 0; i < word.length(); i++) {
                    index = S.indexOf(word.charAt(i), index + 1);
                    if (index < 0) {
                        sub = false;
                        break;
                    }
                }
                if (sub) {
                    count++;
                    cache.put(word, true);
                } else {
                    cache.put(word, false);
                }
            }
        }
        return count;
    }

    @Test
    public void test(){
        System.out.println(numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"}));
    }
}
