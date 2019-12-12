package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSubstring30 {
    /*
    Method1: 暴力 遍历长string
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        //HashMap1 存所有单词
        Map<String, Integer> allWords = new HashMap<>();
        for (String w : words) {
            allWords.put(w, allWords.getOrDefault(w, 0) + 1);
        }
        //遍历所有子串
        for (int start = 0; start < s.length() - wordNum * wordLen + 1; start++) {
            //HashMap2 存当前扫描的字符串含有的单词
            Map<String, Integer> hasWords = new HashMap<>();
            int num = 0;
            //判断该子串是否符合
            while (num < wordNum) {
                String word = s.substring(start + num * wordLen, start + (num + 1) * wordLen);
                //判断该单词在 HashMap1 中
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    //判断当前单词的 value 和 HashMap1 中该单词的 value
                    if (hasWords.get(word) > allWords.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }
            //判断是不是所有的单词都符合条件
            if (num == wordNum) {
                res.add(start);
            }
        }
        return res;
    }



    /*
     Method2: 从最后一个单词遍历，跳着来, Faster
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (words.length == 0 || s.length() < words.length * words[0].length()) {
            return list;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        int listLen = words.length;
        int wordLen = words[0].length();

        for (int i = 0; i < wordLen; i++) {
            for (int j = i; j <= s.length() - wordLen * listLen; j += wordLen) {
                Map<String, Integer> map2 = new HashMap<>();
                for (int k = listLen - 1; k >= 0; k--) { //倒着算，因为能快速跳过去
                    String temp = s.substring(j + k * wordLen, j + (k + 1) * wordLen);
                    int val = map2.getOrDefault(temp, 0) + 1;
                    if (val > map.getOrDefault(temp, 0)) {
                        j += k * wordLen;
                        break;
                    }
                    if (k == 0) {
                        list.add(j);
                    } else {
                        map2.put(temp, val);
                    }
                }
            }
        }
        return list;
    }

    @Test
    public void test() {
        System.out.println(findSubstring2("barfoothefoobarman",new String[]{"foo","bar"}));;
//        System.out.println(findSubstring("wordgoodgoodgoodbestword",new String[]{"word","good","best","word"}));;
//        System.out.println(findSubstring("barfoofoobarthefoobarman",new String[]{"bar","foo","the"}));;
//        System.out.println(findSubstring2("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
//        System.out.println(findSubstring("abaababbaba",new String[]{"ab","ba","ab","ba"}));;
//        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        ;
    }


}
