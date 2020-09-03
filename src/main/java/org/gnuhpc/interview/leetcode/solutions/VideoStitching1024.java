package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/9/2
 */
public class VideoStitching1024 {

    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, (o1, o2) -> (o1[0] - o2[0]));
        int res = 0;
        int index = 0;
        int end = -1;
        while (index < clips.length && clips[index][0] == 0) {
            if (clips[index][1] > end) end = clips[index][1];
            index++;
        }
        if (end == -1) return -1;
        res++;
        if (end >= T) return 1;
        int new_end = 0;
        while (index < clips.length) {
            while (index < clips.length && clips[index][0] <= end) {
                new_end = Math.max(new_end, clips[index][1]);
                index++;
            }
            if (new_end >= T) return ++res;
            res++;
            index++;
            end = new_end;
        }
        return -1;
    }

    @Test
    public void test() {
        System.out.println(videoStitching(new int[][]{
                {0, 2},
                {4, 6},
                {8, 11},
                {1, 9},
                {1, 5}
        }, 10));
    }
}
