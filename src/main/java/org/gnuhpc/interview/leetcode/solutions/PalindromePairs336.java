package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.gnuhpc.interview.leetcode.utils.Utils.isPalindrome;
import static org.gnuhpc.interview.leetcode.utils.Utils.reverseStr;

public class PalindromePairs336 {

    /*
    Trie 方法, 太复杂
     */
    class TrieNode {
        TrieNode[] sons = new TrieNode[26];
        boolean isLeaf;
        int idx;
    }

    TrieNode root;

    boolean reverse = false;

    List<List<Integer>> helper(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            ans.addAll(find(words[i], i));
        }
        return ans;
    }

    void insert(String str, int idx) {
        TrieNode d = root;
        for (char c : str.toCharArray()) {
            if (d.sons[c - 'a'] == null) {
                d.sons[c - 'a'] = new TrieNode();
            }
            d = d.sons[c - 'a'];
        }
        d.isLeaf = true;
        d.idx = idx;
    }

    List<List<Integer>> find(String str, int idx) {
        List<List<Integer>> ans = new ArrayList<>();
        char[] charArray = str.toCharArray();
        TrieNode d = root;
        // deal with empty string as input
        if (d.isLeaf && d.idx != idx && checkPalindromePairs(charArray, 0, charArray.length - 1)) {
            ans.add(reverse ? Arrays.asList(idx, d.idx) : Arrays.asList(d.idx, idx));
        }
        for (int i = charArray.length - 1; i >= 0; i--) {
            d = d.sons[charArray[i] - 'a'];
            if (d == null) {
                break;
            }
            //当d是叶子节点且不是自己的时候，判断剩下的是不是也是
            if (d.isLeaf && d.idx != idx && checkPalindromePairs(charArray, 0, i - 1)) {
                // found a word, how check whether the remainder of str is palin
                ans.add(reverse ? Arrays.asList(idx, d.idx) : Arrays.asList(d.idx, idx));
            }
        }
        return ans;
    }

    boolean checkPalindromePairs(char[] A, int lo, int hi) {
        if (reverse && lo > hi) {
            return false;
        }
        while (lo < hi) {
            if (A[lo++] != A[hi--]) {
                return false;
            }
        }
        return true;
    }


    /**
     * @param words: a list of unique words
     * @return: all pairs of distinct indices
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = helper(words);
        for (int i = 0; i < words.length; i++) {
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }

        reverse = !reverse;

        ans.addAll(helper(words));

        return ans;
    }

    /*
    Hash方法
     */
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        //build the distanceMap save the key-val pairs: word - idx
        HashMap<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            idxMap.put(words[i], i);
        }

        //special cases: "" can be combine with any palindrome string
        if (idxMap.containsKey("")) {
            int blankIdx = idxMap.get("");
            for (int i = 0; i < words.length; i++) {
                if (i != blankIdx && isPalindrome(words[i])) {
                    res.add(Arrays.asList(blankIdx, i));
                    res.add(Arrays.asList(i, blankIdx));
                }
            }
        }

        //find all string and reverse string pairs 查找正好反过来的子串对
        for (int i = 0; i < words.length; i++) {
            String rStr = reverseStr(words[i]);
            if (idxMap.containsKey(rStr)) {
                int found = idxMap.get(rStr);
                if (found != i) {//不是自己, 例如aaa
                    res.add(Arrays.asList(i, found));
                }
            }
        }

        //find the pair s1, s2 like below， 这个非常考验分析能力
        //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1), e.g. cab,ba => (ba,cab)
        //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2), e.g. cab,ac => (cab,ac)
        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
            for (int cut = 1; cut < cur.length(); cut++) {
                if (isPalindrome(cur.substring(0, cut))) {
                    String cut_r = reverseStr(cur.substring(cut));
                    if (idxMap.containsKey(cut_r)) {
                        int found = idxMap.get(cut_r);
                        if (found != i) {//不是自己, 例如aaa
                            res.add(Arrays.asList(found, i));
                        }
                    }
                }
                if (isPalindrome(cur.substring(cut))) {
                    String cut_r = reverseStr(cur.substring(0, cut));
                    if (idxMap.containsKey(cut_r)) {
                        int found = idxMap.get(cut_r);
                        if (found != i) {//不是自己, 例如aaa
                            res.add(Arrays.asList(i, found));
                        }
                    }
                }
            }
        }

        return res;
    }


    @Test
    public void test() {
        System.out.println(palindromePairs(new String[]{"bac", "ac", "aaa", "cab", "ab", ""}));
    }

}
