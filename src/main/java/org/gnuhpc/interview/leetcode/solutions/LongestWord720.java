package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/3/6
 */
public class LongestWord720 {
    int longestLen = 0;
    String ansLongerWord = "";

    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        for (String s : words) {
            TrieNode cur = root;
            for (char c : s.toCharArray()) {
                if (cur.children[c - 'a'] == null)
                    cur.children[c - 'a'] = new TrieNode();
                cur = cur.children[c - 'a'];
            }
            cur.isEnd = true;
            cur.word = s;
        }

        longestWordDFS(root, 0);
        return ansLongerWord;
    }

    public void longestWordDFS(TrieNode root, int depth) {
        if (root == null || (depth > 0 && !root.isEnd)) //当前节点为空，或者当前节点（非根节点）不是单词的结尾时，return剪枝
            return;

        //每次搜索更新最大深度和最长单词
        if (depth > longestLen) {
            longestLen = depth;
            ansLongerWord = root.word;
        }

        for (TrieNode node : root.children)
            if (node != null)
                longestWordDFS(node, depth + 1);
    }

    class TrieNode {
        String word;
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
}
