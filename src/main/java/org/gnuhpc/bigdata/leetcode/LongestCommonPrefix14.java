package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.trie.Trie;
import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class LongestCommonPrefix14 {
    /*
    排序法
     */
    //O(nlogn)
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        Arrays.sort(strs);

        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length-1].toCharArray();

        int i;
        for (i = 0; i < Math.min(first.length,last.length) && first[i] == last[i]; i++);

        return strs[0].substring(0,i);
    }

    /*
    Method2: Trie Prefix
     */
    public String longestCommonPrefix2(String[] strs) {
        Trie trie = new Trie();

        for (String s:strs) trie.insert(s);

        return trie.longestCommonPrefix();
    }


    /*
    Metho3: 二分 TODO: 二分直接猜答案, 适用于答案在一个区间的题目
     */
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }

    @Test
    public void test(){
        String[] strs = new String[]{"al", "all", "alp", "alde"};
        assertEquals("al", longestCommonPrefix(strs));
        assertEquals("al", longestCommonPrefix2(strs));
        assertEquals("al", longestCommonPrefix3(strs));
    }

    // add by tina,1ms
    // 暴力解 o(n^2)
    public String longestCommonPrefix4(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        StringBuilder sb = new StringBuilder("");
        int flen = strs[0].length();
        int j = 0;
        while(j<flen){
            char c = strs[0].charAt(j);
            for(int i = 1;i<strs.length;i++){
                if(j>=strs[i].length()
                        ||strs[i].charAt(j) != c) return sb.toString();
            }
            sb.append(c);
            j++;
        }
        return sb.toString();

    }
}
