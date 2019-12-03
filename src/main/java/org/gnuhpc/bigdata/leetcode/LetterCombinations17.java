package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinations17 {
    //Method 1: DFS + Backtracking 这种可以直观理解为钻到底

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
        helper(digits, res, new StringBuilder(), digits.length());

        return res;
    }

    private void helper(String digits, List<String> res, StringBuilder sb, int length) {
        if (sb.length() == length) {
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i < digits.length(); i++) {
            for (char c : keyMap.get(digits.charAt(i)).toCharArray()) {
                sb.append(c);
                helper(digits.substring(i+1), res, sb, length);
                sb.deleteCharAt(sb.length()-1);
            }
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
    public void test(){
        String digits = "23";

        List<String> res = letterCombinations2(digits);

        System.out.println(res);
    }


}
