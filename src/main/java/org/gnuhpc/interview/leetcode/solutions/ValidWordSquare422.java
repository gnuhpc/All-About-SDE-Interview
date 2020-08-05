package org.gnuhpc.interview.leetcode.solutions;

import java.util.List;

public class ValidWordSquare422 {
    public boolean validWordSquare(List<String> words) {
        // 获取二维数组最大尺寸
        int maxLen = 0;
        for (int x = 0; x < words.size(); x++) {
            if (words.get(x).length() > maxLen) {
                maxLen = words.get(x).length();
            }
        }
        int max = maxLen > words.size() ? maxLen : words.size();
        // 初始化二维数组char[][]，将输入存入
        char[][] format = new char[words.size()][max];
        for (int i = 0; i < words.size(); i++) {
            char[] word = words.get(i).toCharArray();
            for (int j = 0; j < word.length; j++) {
                format[i][j] = word[j];
            }
        }
        // 比较对应位置是否相同
        for (int m = 0; m < format.length; m++) {
            for (int n = 0; n < format[m].length; n++) {
                try{
                    if (format[m][n] != format[n][m]) {
                        return false;
                    }
                } catch (Exception e){ //注意捕获异常
                    return false;
                }
            }
        }
        return true;
    }
}
