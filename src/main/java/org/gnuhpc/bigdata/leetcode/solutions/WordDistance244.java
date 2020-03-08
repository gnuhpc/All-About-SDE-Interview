package org.gnuhpc.bigdata.leetcode.solutions;

/**
 * This is a follow up of Shortest Word Distance 243.
 * The only difference is now you are given the list of words
 * and your method will be called repeatedly many times
 * with different parameters. How would you optimize it?
 * <p>
 * Design a class which receives a list of words
 * in the constructor, and implements a method
 * that takes two words word1 and word2
 * and return the shortest distance between these two words
 * in the list.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * 本题与上一题的不同，要求能支持重复调用
 * 这意味着，我们最好能用一个结构去维护单词及其出现的位置，即HashMap
 * 这样我们计算单词的最小距离时，就变成计算2个有序数组的的最小距离。
 * 不用重新遍历原String数组
 */

public class WordDistance244 {
    private Map<String, List<Integer>> hmap = new HashMap<>();

    // 构造函数
    public WordDistance244(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            List<Integer> list = hmap.get(word);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(i);
            hmap.put(word, list);
        }
    }

    // 抽象出的问题：求2个有序数组的最短距离
    // 双指针
    // 抽象出的问题：求2个有序数组的最短距离
    // 因为同一组参数可能会被重复调用，我们可以加一个cache,提升效率
    private Map<String, Integer> resMap = new HashMap<>();

    public int shortest(String word1, String word2) {
        String word = word1.compareTo(word2) < 0 ? word1 + word2 : word2 + word1;
        if (resMap.containsKey(word)) return resMap.get(word);

        List<Integer> lst1 = hmap.get(word1);
        List<Integer> lst2 = hmap.get(word2);
        int i = 0;
        int j = 0;
        int res = Integer.MAX_VALUE;
        // 首先判定是个while循环来做，循环结束的条件
        // -有一个列表遍历完毕
        while (i < lst1.size() && j < lst2.size()) {
            res = Math.min(res, Math.abs(lst1.get(i) - lst2.get(j)));
            if (lst1.get(i) < lst2.get(j)) i++;
            else j++;
        }

        resMap.put(word, res);

        return res;
    }


    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        WordDistance244 wd = new WordDistance244(words);
        System.out.println(wd.shortest("coding", "makes"));

    }


}
