package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/1/10
 */
public class LengthLongestPath388 {
    public int lengthLongestPath(String input) {
        int res = 0;
        String[] splits = input.split("\\n");

        Map<Integer, Integer> map = new HashMap<>();

        int level = 0;

        for (String line : splits) {
            String processed = line.replace("\t", "");
            level = line.length() - processed.length();
            int length = processed.length();
            map.put(level, length);

            if (line.indexOf(".") != -1) {
                int sum = 0;
                for (int i = 0; i <= level; i++) {
                    sum += (map.get(i) + 1);//加1是加斜杠
                }

                res = Math.max(res, sum - 1);//减1是减最后的那个不应该加的斜杠（因为是文件）
            }
        }

        return res;
    }

    @Test
    public void test() {
//        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2"));
    }
}
