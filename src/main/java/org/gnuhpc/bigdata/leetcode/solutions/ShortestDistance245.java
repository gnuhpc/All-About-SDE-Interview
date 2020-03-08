package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

public class ShortestDistance245 {
    public int shortestDistance(String[] words, String word1, String word2) {
        int p = -1; //初始化为-1，初始值不参与距离计算
        int q = -1;
        int res = Integer.MAX_VALUE;

        int n = words.length;
        for (int i = 0; i < n; i++) {
            // 发现单词匹配，直接更新位置
            if (words[i].equals(word1)) {
                p = i;
                // 对位置进行判断，都出现过，才计算距离
                if (q != -1) res = Math.min(res, Math.abs(p - q));
            }
            if (words[i].equals(word2)) {
                q = i;
                //如果p和q是同一个位置,说明words[i] = word1 = word2, 这个距离是无效的,因此跳过去不计算就可以了.
                if (p != q && p != -1) res = Math.min(res, Math.abs(p - q));
            }
        }
        return res;
    }


    @Test
    public void test() {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "makes";
        String word2 = "makes";
        System.out.println(shortestDistance(words, word1, word2));
    }

}
