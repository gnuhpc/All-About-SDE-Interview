package org.gnuhpc.bigdata.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/1/10
 */
public class IsRectangleOverlap836 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left //2 不动
                 rec1[3] <= rec2[1] ||   // bottom
                 rec1[0] >= rec2[2] ||   // right
                 rec1[1] >= rec2[3]);    // top
    }

}
