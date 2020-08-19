package org.gnuhpc.interview.leetcode.solutions;

import java.util.List;

public class MaxLength1239 {
    int res;
    public int maxLength(List<String> arr) {
        res = 0;
        dfs(arr, 0, "");
        return res;
    }
    private void dfs(List<String> arr, int pos, String cur) {
        if(pos == arr.size()) {
            res = Math.max(res, cur.length());
            return;
        }
        String s = arr.get(pos);
        boolean valid = true;
        for(int i = 0; i < s.length(); ++i) {
            //自己有重复的或者和cur有重复的都不行
            if(s.lastIndexOf(s.charAt(i)) > i || cur.contains("" + s.charAt(i))) {
                valid = false;
                break;
            }
        }
        if(valid) dfs(arr, pos + 1, cur + s);
        dfs(arr, pos + 1, cur);
    }
}
