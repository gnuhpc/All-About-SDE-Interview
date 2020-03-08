package org.gnuhpc.bigdata.leetcode.solutions;

import com.google.inject.internal.util.$StackTraceElements;
import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2020/1/10
 */
public class WordsAbbreviation527 {
    public List<String> wordsAbbreviation(List<String> dict) {
        if (dict == null) return null;
        int len = dict.size();
        List<String> ans = new ArrayList<>();
        // 阶段数
        int[] prefix = new int[len];
        // key: ans, value: count
        Map<String, Integer> count = new HashMap<>();

        for (int i = 0; i < len; i++) {
            prefix[i] = 1;
            ans.add(getAbbr(dict.get(i), prefix[i]));
            count.put(ans.get(i), count.getOrDefault(ans.get(i), 0) + 1);
        }

        while (true) {
            boolean isUnique = true;
            for (int i = 0; i < len; i++) {
                if (count.get(ans.get(i)) > 1) {
                    prefix[i]++;
                    ans.set(i, getAbbr(dict.get(i), prefix[i]));
                    count.put(ans.get(i), count.getOrDefault(ans.get(i), 0) + 1);
                    isUnique = false;
                }
            }
            if (isUnique) break;
        }
        return ans;
    }

    public String getAbbr(String s, int p) {
        if (p >= s.length() - 2) {
            return s;
        }
        String ans;
        ans = s.substring(0, p) + (s.length() - 1 - p) + s.charAt(s.length() - 1);
        return ans;
    }

}
