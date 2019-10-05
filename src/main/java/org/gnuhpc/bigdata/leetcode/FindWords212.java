package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.trie.Trie;
import org.gnuhpc.bigdata.datastructure.trie.TrieNode;

import java.util.*;

//TODO 二维匹配 Trie 快速剪枝
public class FindWords212 {
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words)
            trie.insert(word);

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        Set<String> resultSet = new HashSet<>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j)
                search(board, visited, i, j, m, n, trie.root, resultSet);
        }

        return new LinkedList<String>(resultSet);
    }


    private void search(char[][] board,
                        boolean[][] visited,
                        int i,
                        int j,
                        int m,
                        int n,
                        TrieNode node,
                        Set<String> result) {
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j])
            return;

        node = node.children[board[i][j] - 'a'];
        if (node == null)
            return;

        if (node.word != null)
            result.add(node.word);

        visited[i][j] = true;
        search(board, visited, i - 1, j, m, n, node, result);
        search(board, visited, i + 1, j, m, n, node, result);
        search(board, visited, i, j - 1, m, n, node, result);
        search(board, visited, i, j + 1, m, n, node, result);
        visited[i][j] = false;
    }
}
