package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright gnuhpc 2021/2/18
 */
public class LargeGroupPositions830 {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int left = 0;//相同字符串左边边界
        int length = s.length();
        while (left < length) {
            int count = 0;//相同字符串的长度
            //统计相同字符串的长度
            while (left + count < length && s.charAt(left) == s.charAt(left + count))
                count++;
            //如果长度大于等于3，就把他加入到res中
            if (count >= 3)
                res.add(Arrays.asList(left, left + count - 1));
            //更新下一个字符串的左边边界
            left = left + count;
        }
        return res;
    }

}
