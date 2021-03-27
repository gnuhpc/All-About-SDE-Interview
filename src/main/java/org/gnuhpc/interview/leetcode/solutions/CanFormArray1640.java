package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class CanFormArray1640 {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> numPosMap = new HashMap<>(); // 数字-位置映射
        for (int i = 0; i < arr.length; i++) {
            numPosMap.put(arr[i], i);
        }
        int count = 0;
        for (int[] piece : pieces) {
            if (piece.length == 0) continue;
            if (!numPosMap.containsKey(piece[0])) return false;
            int first = numPosMap.get(piece[0]); // 查找首位
            for (int i = 1; i < piece.length; i++) { // 匹配余下项
                if (first + i >= arr.length || arr[first + i] != piece[i]) return false;
            }
            count += piece.length;
        }
        return count == arr.length;
    }
}
