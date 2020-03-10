package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.trie.Trie;
import org.gnuhpc.interview.datastructure.trie.TrieNode;
import org.junit.Test;

import java.util.*;

public class FindWords212 {
    private int[][] dr = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };
    private int m, n;
    private boolean[][] visited;

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words)
            trie.insert(word);

        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        Set<String> resultSet = new HashSet<>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j)
                dfs(board, i, j, trie.root, resultSet);
        }

        return new LinkedList<>(resultSet);
    }


    private void dfs(char[][] board,
                     int x,
                     int y,
                     TrieNode node,
                     Set<String> result) {
        node = node.children[board[x][y] - 'a'];
        if (node == null) return;

        if (node.isLeaf) {
            result.add(node.word);
        }

        visited[x][y] = true;
        for (int[] d : dr) {
            int newX = x + d[0];
            int newY = y + d[1];
            if (isValid(newX, newY)) {
                dfs(board, newX, newY, node, result);
            }
        }
        visited[x][y] = false;
    }

    private boolean isValid(int x, int y) {
        return ((x >= 0 && x < m) && (y >= 0 && y < n) && !visited[x][y]);
    }

    @Test
    public void test() {
        System.out.println(findWords(new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        }, new String[]{"oath", "pea", "eat", "rain"}));
    }
}
