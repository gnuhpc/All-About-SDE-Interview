package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class ShortestDistance243 {
    public int shortestDistance(String[] words, String word1, String word2) {
        int p = -1; //初始化为-1，初始值不参与距离计算
        int q = -1;
        int res = Integer.MAX_VALUE;

        int n = words.length;
        for(int i = 0; i<n; i++){
            // 发现单词匹配，直接更新位置
            if(words[i].equals(word1)) {
                p = i;
                // 对位置进行判断，都出现过，才计算距离
                if( q != -1) res = Math.min(res,Math.abs(p-q));
            }
            if(words[i].equals(word2)) {
                q = i;

                if( p != -1) res = Math.min(res,Math.abs(p-q));
            }
        }
        return res;
    }



    @Test
    public void test(){
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding";
        String word2 = "makes";
        System.out.println(shortestDistance(words,word1,word2));
    }

}
