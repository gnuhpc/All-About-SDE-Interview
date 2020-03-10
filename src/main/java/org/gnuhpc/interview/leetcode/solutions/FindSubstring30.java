package org.gnuhpc.interview.leetcode.solutions;

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
     Method2: 双指针
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words.length == 0 || s.length() < words.length * words[0].length()) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();

        int windowSize = 0;
        int wordSize = words[0].length();

        for (String word : words) {
            windowSize += word.length();
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        if (s.length() < windowSize) return res;

        Map<String, Integer> origMap = new HashMap<>(map);

        int left = 0, right = 0, counter = map.size();


        //外层遍历只需要遍历wordSize大小即可，因为后边都是根据这个单元进行跳着走的
        for (int i = 0; i < wordSize; i++) {
            left = i;
            right = i;
            map.clear(); // reset to original frequency table after every iteration
            map.putAll(origMap);
            counter = map.size();

            while (right + wordSize - 1 < s.length()) {
                String lastword = s.substring(right, right + wordSize);

                if (map.containsKey(lastword)) {
                    map.put(lastword, map.get(lastword) - 1);
                    if (map.get(lastword) == 0) counter--;
                }
                right += wordSize;

                if (right - left == windowSize) {
                    // counter == 0, valid answer !
                    if (counter == 0) {
                        res.add(left);
                    }

                    String firstword = s.substring(left, left + wordSize);

                    if (map.containsKey(firstword)) {
                        map.put(firstword, map.get(firstword) + 1);
                        if (map.get(firstword) > 0) counter++;
                    }

                    left += wordSize;
                }

            }
        }

        return res;
    }

    @Test
    public void test() {
        System.out.println(findSubstring2("barfoothefoobarman", new String[]{"foo", "bar"}));
        ;
//        System.out.println(findSubstring("wordgoodgoodgoodbestword",new String[]{"word","good","best","word"}));;
//        System.out.println(findSubstring("barfoofoobarthefoobarman",new String[]{"bar","foo","the"}));;
//        System.out.println(findSubstring2("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
//        System.out.println(findSubstring("abaababbaba",new String[]{"ab","ba","ab","ba"}));;
//        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        ;
    }


}
