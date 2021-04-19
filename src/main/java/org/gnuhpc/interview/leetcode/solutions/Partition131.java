package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.gnuhpc.interview.leetcode.utils.Utils.isPalindrome;
//https://leetcode-cn.com/problems/palindrome-partitioning/solution/131-fen-ge-hui-wen-chuan-hui-su-sou-suo-suan-fa-xi/

public class Partition131 {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), s, 0);
        return res;
    }

    public void backtrack(List<List<String>> res, List<String> tempList, String s, int split) {
        if (split == s.length()) {
            res.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = split; i < s.length(); i++) {
            if (isPalindrome(s, split, i)) {
                tempList.add(s.substring(split, i + 1));
                backtrack(res, tempList, s, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    @Test
    public void test() {
        System.out.println(partition("aab"));
    }

}
