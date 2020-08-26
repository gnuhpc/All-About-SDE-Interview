package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/8/18
 */
public class JudgeCircle657 {
    public boolean judgeCircle(String moves) {
        Map<Character, int[]> map = new HashMap<>();
        map.put('U', new int[]{0, 1});
        map.put('D', new int[]{0, -1});
        map.put('L', new int[]{-1, 0});
        map.put('R', new int[]{1, 0});
        char[] arr = moves.toCharArray();
        int x = 0;
        int y = 0;

        for (char m : arr) {
            int[] move = map.get(m);

            x += move[0];
            y += move[1];
        }

        return x == 0 && y == 0;
    }
}
