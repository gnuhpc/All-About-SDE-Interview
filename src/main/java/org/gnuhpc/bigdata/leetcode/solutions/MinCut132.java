package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.isPalindrome;

//https://leetcode.com/problems/Palindrome-Partitioning-II/discuss/175370/Java-DFS-Recursion-with-cache
public class MinCut132 {
    //We cache each positions minimum depth from this position by min depth found- current depth(cut) .
    // If we hit a position again then we return the cut (current depth)+ minimum depth for this position.
    private int gen(String s, int pos, int cut, Map<Integer, Integer> cache) {
        if (pos >= s.length()) return cut - 1;
        int min = Integer.MAX_VALUE;
        if (cache.containsKey(pos)) {
            return cut + cache.get(pos);
        }
        for (int end = pos + 1; end <= s.length(); ++end) {
            String sub = s.substring(pos, end);
            if (isPalindrome(sub)) {
                min = Math.min(min, gen(s, end, cut + 1, cache));
            }
        }
        cache.put(pos, min - cut);
        return min;
    }

    public int minCut(String s) {
        return gen(s, 0, 0, new HashMap<>());
    }

}
