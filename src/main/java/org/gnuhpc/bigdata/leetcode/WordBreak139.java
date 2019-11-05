package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.trie.Trie;
import org.junit.Test;

import java.util.*;

public class WordBreak139 {
    /*
    Method 1: DFS Brute force , LTE
     */
    private int min = 0, max = 0;
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for (String word : wordDict) {
            int l = word.length();
            max = Math.max(l, max);
            min = Math.min(l, min);
        }

        return canBreak(0, s, wordSet);
    }

    boolean canBreak(int start, String s, Set<String> wordSet) {
        if (start == s.length()) return true;
        for (int end = start + min; end <= s.length(); end++) {
            String subStr = s.substring(start, end);
            if(isValid(subStr, wordSet)){
                if(canBreak(end, s, wordSet))
                    return true;
            }
        }
        return false;
    }

    private boolean isValid(String subStr, Set<String> wordSet) {
        boolean b1 = (subStr.length() <= max);
        boolean b2 = wordSet.contains(subStr);

        return b1 && b2;
    }

    /*
    Method 2: dfs + Memorization 7ms
     */

    public boolean wordBreak2(String s, List<String> wordDict) {
        int[] cache = new int[s.length() + 1];
        Arrays.fill(cache, -1);
        Set<String> wordSet = new HashSet<>(wordDict);
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for (String word : wordDict) {
            int l = word.length();
            max = Math.max(l, max);
            min = Math.min(l, min);
        }

        return dfs(0, s, wordSet, cache);
    }

    // -1 -- init 0 -- true 1-- false
    private boolean dfs(int start, String s, Set<String> wordDict, int[] cache) {
        if (start == s.length()) {
            cache[start] = 1;
            return true;
        }

        if (cache[start] != -1) return cache[start] == 1;

        for (int end = start + min; end <= s.length(); end++) {
            String subStr = s.substring(start, end);
            if (isValid(subStr,wordDict)){
                if (dfs(end,s,wordDict,cache)){
                    cache[start] = 1;
                    return true;
                }
            }
        }

        cache[start] = 0;
        return false;
    }

    /*
    Method 3: DP //DP做分割题目
    这个方法的想法是对于给定的字符串（ss）可以被拆分成子问题 s1s1 和 s2s2 。如果这些子问题都可以独立地被拆分成符合要求的子问题，那么整个问题 ss 也可以满足。也就是，如果 "\text{catsanddog}catsanddog" 可以拆分成两个子字符串 "\text{catsand}catsand" 和 "\text{dog}dog" 。子问题 "\text{catsand}catsand" 可以进一步拆分成 "\text{cats}cats" 和 "\text{and}and" ，这两个独立的部分都是字典的一部分，所以 "\text{catsand}catsand" 满足题意条件，再往前， "\text{catsand}catsand" 和 "\text{dog}dog" 也分别满足条件，所以整个字符串 "\text{catsanddog}catsanddog" 也满足条件。

现在，我们考虑 \text{dp}dp 数组求解的过程。我们使用 n+1n+1 大小数组的 \text{dp}dp ，其中 nn 是给定字符串的长度。我们也使用 2 个下标指针 ii 和 jj ，其中 ii 是当前字符串从头开始的子字符串（s's
′
 ）的长度， jj 是当前子字符串（s's
′
 ）的拆分位置，拆分成 s'(0,j)s
′
 (0,j) 和 s'(j+1,i)s
′
 (j+1,i) 。

为了求出 \text{dp}dp 数组，我们初始化 \text{dp}[0]dp[0] 为 \text{true}true ，这是因为空字符串总是字典的一部分。 \text{dp}dp 数组剩余的元素都初始化为 \text{false}false 。

我们用下标 ii 来考虑所有从当前字符串开始的可能的子字符串。对于每一个子字符串，我们通过下标 jj 将它拆分成 s1's1
′
  和 s2's2
′
  （注意 ii 现在指向 s2's2
′
  的结尾）。为了将 \text{dp}[i]dp[i] 数组求出来，我们依次检查每个 \text{dp}[j]dp[j] 是否为 \text{true}true ，也就是子字符串 s1's1
′
  是否满足题目要求。如果满足，我们接下来检查 s2's2
′
  是否在字典中。如果包含，我们接下来检查 s2's2
′
  是否在字典中，如果两个字符串都满足要求，我们让 \text{dp}[i]dp[i] 为 \text{true}true ，否则令其为 \text{false}false 。

作者：LeetCode
链接：https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public boolean wordBreak3(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];

        dp[0] = true;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                if (dp[i] && wordDict.contains(s.substring(i, j))) {
                    dp[j] = true;
                }
            }
        }

        return dp[len];
    }

    /*
    Method 4: Trie
     */

    public boolean wordBreak4(String s, List<String> wordDict) {
        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.insert(word);
        }
        HashMap<String, Boolean> memo = new HashMap<>();
        return canBreak(s, trie, memo);
    }

    private boolean canBreak(String s, Trie trie, HashMap<String, Boolean> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        for (int i = 1; i <= s.length(); i++) {
            if (trie.startsWith(s.substring(0, i))) {
                if (trie.search(s.substring(0, i))) {
                    if (canBreak(s.substring(i), trie, memo)) {
                        memo.put(s, true);
                        return true;
                    }
                }
                if (i == s.length()) {
                    if (!trie.search(s)) {
                        memo.put(s, false);
                        return false;
                    }
                }
            }
            else {
                memo.put(s, false);
                return false;
            }
        }
        memo.put(s, true);
        return true;
    }

    /*
    Method 5: bfs
    We can use a graph to represent the possible solutions. The vertices of the graph are simply the positions of the first characters of the words and each edge actually represents a word. For example, the input string is "nightmare", there are two ways to break it, "night mare" and "nightmare". The graph would be
0-->5-->9
|__ __ _^
The question is simply to check if there is a path from 0 to 9. The most efficient way is traversing the graph using BFS with the help of a queue and a hash set. The hash set is used to keep track of the visited nodes to avoid repeating the same work.
For this problem, the time complexity is O(n^2) and space complexity is O(n), the same with DP. This idea can be used to solve the problem word break II. We can simple construct the graph using BFS, save it into a map and then find all the paths using DFS
     */

    public boolean wordBreak5(String s, List<String> dict) {
        Set<String> wordDict = new HashSet<>(dict);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for (String word : wordDict) {
            int l = word.length();
            max = Math.max(l, max);
            min = Math.min(l, min);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int n = queue.poll();
                if (n == s.length()) { //可以到最后就是true
                    return true;
                }
                if (!visited.contains(n)) {
                    for (int i = n + 1; i <= s.length(); i++) {
                        if (dict.contains(s.substring(n, i))) {
                            queue.add(i);
                            visited.add(n);
                        }
                    }
                }
            }
        }
        return false;

    }



    @Test
    public void test() {
        System.out.println(wordBreak5("leetcodea", Arrays.asList("leet","code")));
    }
}
