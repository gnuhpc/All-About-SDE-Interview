package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class LetterCombinations17 {
    //Method 1: DFS (+ Backtracking, if use StringBuilder/ArrayList instead of String) 这种可以直观理解为钻到底

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

    Map<Integer, char[]> map = new HashMap<>();
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations2(String digits) {
        if(digits==null || digits.length()==0) return res;

        map.put(2, new char[]{'a','b','c'});
        map.put(3, new char[]{'d','e','f'});
        map.put(4, new char[]{'g','h','i'});
        map.put(5, new char[]{'j','k','l'});
        map.put(6, new char[]{'m','n','o'});
        map.put(7, new char[]{'p','q','r','s'});
        map.put(8, new char[]{'t','u','v'});
        map.put(9, new char[]{'w','x','y','z'});

        dfs(new ArrayList<>(), digits, 0);

        return res;
    }

    private void dfs(List<Character> temp, String digits, int start){
        if(temp.size() == digits.length()){
            StringBuilder sb = new StringBuilder();
            for(char c: temp) sb.append(c);
            res.add(sb.toString());
            return;
        }

        char[] charList = map.get(digits.charAt(start)-'0');
        for(char c: charList){
            temp.add(c);
            dfs(temp,digits,start+1);
            temp.remove(temp.size()-1);
        }

        return;
    }


    //method 2: BFS，这种可以理解为一层层准备拿出来和下一层合并
    public List<String> letterCombinations3(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.isEmpty()) {
            return ans;
        }
        Queue<String> q = new LinkedList<>();
        q.add("");
        int idx = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.length() == digits.length()) {
                    ans.add(cur);
                    continue;
                }
                char[] charList = map.get(digits.charAt(idx)-'0');
                for (char c : charList) {
                    q.offer(cur + c);
                }
            }

            idx++;
        }
        return ans;
    }

    @Test
    public void test() {
        String digits = "23";

        List<String> res = letterCombinations2(digits);

        System.out.println(res);
    }


}
