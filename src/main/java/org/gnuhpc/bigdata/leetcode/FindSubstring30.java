package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSubstring30 {
    /*

    遍历长string

     */
    private Map<String, Integer> wordCountMap = new HashMap<>();
    private List<Integer> res = new ArrayList<>();

    public List<Integer> findSubstring(String s, String[] words) {
        if (words == null || words.length == 0) return res;
        int length = words[0].length();
        int count = words.length;

        for (String word : words) {
            wordCountMap.put(word,
                    wordCountMap.containsKey(word) ? wordCountMap.get(word) + 1 : 1);
        }

        for (int i = 0; i <= s.length() - count * length; i++) {
            Map<String, Integer> copyMap = new HashMap<>(wordCountMap);

            for (int j = 0; j < words.length; j++) {
                String subStr = s.substring(i + j * length, i + (j + 1) * length);

                if (copyMap.containsKey(subStr)) {
                    Integer counts = copyMap.get(subStr);
                    if (counts == 1) copyMap.remove(subStr);
                    else {
                        copyMap.put(subStr, counts - 1);
                    }
                    if (copyMap.isEmpty()) {
                        res.add(i);
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        return res;
    }

    /*
    遍历短的，跳着来, Faster
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
                for (int k = listLen - 1; k >= 0; k--) {
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
//        System.out.println(findSubstring("barfoothefoobarman",new String[]{"foo","bar"}));;
//        System.out.println(findSubstring("wordgoodgoodgoodbestword",new String[]{"word","good","best","word"}));;
//        System.out.println(findSubstring("barfoofoobarthefoobarman",new String[]{"bar","foo","the"}));;
        System.out.println(findSubstring2("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
//        System.out.println(findSubstring("abaababbaba",new String[]{"ab","ba","ab","ba"}));;
//        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        ;
    }
}
