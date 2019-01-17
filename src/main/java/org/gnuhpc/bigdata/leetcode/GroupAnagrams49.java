package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

public class GroupAnagrams49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] sc = s.toCharArray();
            Arrays.sort(sc);
            String key = String.valueOf(sc);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    @Test
    public void test(){
        String[] testArray = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] testArray2 = new String[]{""};

        groupAnagrams(testArray);
//        groupAnagrams(testArray2);
    }
}
