package org.gnuhpc.interview.leetcode.solutions;

import com.google.inject.internal.util.$Collections2;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/3/28
 */
public class MinimumLengthEncoding820 {
    class NodeTrie {
        NodeTrie[] childNode = new NodeTrie[26];

        int childCount;

        NodeTrie getChild(char c) {
            int num = c - 'a';
            if (childNode[num] == null) {
                childNode[num] = new NodeTrie();
                childCount++;
            }
            return childNode[num];
        }
    }

    public int minimumLengthEncoding(String[] words) {
        Map<String, NodeTrie> map = new HashMap<>();
        NodeTrie root = new NodeTrie();
        int sum = 0;
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            NodeTrie nodeTrie = root;
            for (int j = str.length() - 1; j >= 0; j--) {
                nodeTrie = nodeTrie.getChild(str.charAt(j));
            }
            map.put(str, nodeTrie);
        }

        for (Map.Entry<String, NodeTrie> entry : map.entrySet()) {
            if (entry.getValue().childCount == 0) {//下边没有节点了，也就是最长的
                sum += entry.getKey().length() + 1; //+1为添加#
            }
        }
        return sum;
    }
}
