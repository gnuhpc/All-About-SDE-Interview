package org.gnuhpc.interview.leetcode.solutions;

public class MinCostToMoveChips1217 {
    //奇数到奇数没有cost，偶数到偶数没有cost
    public int minCostToMoveChips(int[] position) {
        int odd = 0, even = 0;
        for (int i = 0; i < position.length; i++) {
            if (position[i] % 2 == 0) {
                even++;
            } else if (position[i] % 2 != 0) {
                odd++;
            }
        }
        return Math.min(even, odd);

    }
}
