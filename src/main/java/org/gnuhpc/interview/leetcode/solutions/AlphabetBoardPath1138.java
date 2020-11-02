package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/31
 */
public class AlphabetBoardPath1138 {
    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        int currX = 0, currY = 0;
        for (char c : target.toCharArray()) {
            int m = c - 'a';
            int x = m / 5;    // 目标字符x
            int y = m % 5;    // 目标字符y
            int distanceY = y - currY;
            int distanceX = x - currX;
            if (currX == 5 && currY == 0) {  // 起点如果是'z'，则先走上下，再走左右
                for (int i = 0; i < Math.abs(distanceX); ++i) {
                    sb.append(distanceX > 0 ? 'D' : 'U');
                }
                for (int i = 0; i < Math.abs(distanceY); ++i) {
                    sb.append(distanceY > 0 ? 'R' : 'L');
                }
            } else {
                for (int i = 0; i < Math.abs(distanceY); ++i) {
                    sb.append(distanceY > 0 ? 'R' : 'L');
                }
                for (int i = 0; i < Math.abs(distanceX); ++i) {
                    sb.append(distanceX > 0 ? 'D' : 'U');
                }
            }

            sb.append('!');
            currX = x;
            currY = y;
        }
        return sb.toString();
    }
}
