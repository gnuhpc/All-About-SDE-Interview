package org.gnuhpc.interview.leetcode.solutions;

public class MaxRepeating1668 {
    public int maxRepeating(String sequence, String word) {
        //字符串匹配的问题吧  匹配了几次
        int count = 0 ;
        String tmp = word ;
        while(sequence.contains(word)){
            word+=tmp ;
            count++ ;
        }
        return count ;
    }
}
