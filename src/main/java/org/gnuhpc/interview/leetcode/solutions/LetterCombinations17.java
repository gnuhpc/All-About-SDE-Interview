package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinations17 {
    //Method 1: DFS (+ Backtracking, if use StringBuilder instead of String) 这种可以直观理解为钻到底

    HashMap<Character, String> keyMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return res;
        helper(0, digits.length(), digits, res, "");

        return res;
    }

    private void helper(int curLength, int length, String digits, List<String> res, String temp) {
        if (curLength == length) {
            res.add(temp);
            return;
        }

        char num = digits.charAt(curLength);
        for (int i = 0; i < keyMap.get(num).length(); i++) {
            helper(curLength + 1, length, digits, res, temp + keyMap.get(num).charAt(i));
        }
    }


    //method 2: BFS，这种可以理解为一层层准备拿出来和下一层合并
    public List<String> letterCombinations2(String digits) {

        List<String> l = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return l;
        }

        l.add("");

        //先固定目前要遍历的数字
        for (int i = 0; i < digits.length(); i++) {
            ArrayList<String> temp = new ArrayList<>();
            String option = keyMap.get(digits.charAt(i));

            //然后根据结果集大小,进行遍历，
            // 因为要对这个初步的结果集每个元素进行一遍可能的option的添加
            for (int j = 0; j < l.size(); j++) {
                for (int p = 0; p < option.length(); p++) {
                    temp.add(l.get(j) + option.charAt(p));
                }
            }

            //System.out.println(temp);
            l.clear();
            l.addAll(temp);
        }

        return l;
    }

    @Test
    public void test() {
        String digits = "23";

        List<String> res = letterCombinations2(digits);

        System.out.println(res);
    }


}
