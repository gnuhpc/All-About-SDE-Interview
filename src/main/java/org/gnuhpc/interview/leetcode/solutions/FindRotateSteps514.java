package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
思路：

记录ring上所有字符出现的位置。
递归模拟下一步。
 */
public class FindRotateSteps514 {
    String ring, key;
    List<Integer>[] pos;
    Map<String, Integer> memo = new HashMap<>();
    public int findRotateSteps(String ring, String key){
        this.ring = ring; this.key = key;
        pos = new List[26];
        for(int i = 0; i < ring.length(); i++){
            int p = ring.charAt(i) - 'a';
            if(pos[p] == null) pos[p] = new ArrayList();
            pos[p].add(i);
        }
        return dfs(0, 0);
    }

    /**
     *
     * @param p1  在ring上的位置
     * @param p2  在key上的位置
     * @return
     */
    public int dfs(int p1, int p2){
        String memoKey = p1 +"_" + p2;
        if(memo.containsKey(memoKey)) return memo.get(memoKey);
        if(p2 == key.length())  return 0;
        int p = key.charAt(p2) - 'a';
        int res = Integer.MAX_VALUE;
        for(Integer next: pos[p]){
            int dist = Math.abs(p1 - next);
            dist = Math.min(dist, ring.length() - dist);
            res = Math.min(res, dist +  dfs(next, p2 + 1) + 1);
        }
        memo.put(memoKey, res);
        return res;
    }
}
