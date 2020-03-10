package org.gnuhpc.interview.leetcode.solutions;

import java.util.TreeMap;


public class Trie208 {
    private static final int ALPHABETSIZE = 26;

    class TrieNode {

        public String character;
        public TrieNode[] children;
        public boolean leaf;

        public TrieNode(String character) {
            this.character = character;
            children = new TrieNode[ALPHABETSIZE];
        }

        @Override
        public String toString() {
            return this.character;
        }
    }

    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie208() {
        this.root = new TrieNode("");
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); ++i) {
            int index = word.charAt(i) - 'a';

            if (node.children[index] == null) {
                TrieNode trieNode = new TrieNode(String.valueOf(word.charAt(i)));
                node.children[index] = trieNode;
                node = trieNode;
            } else {
                node = node.children[index];
            }
        }

        node.leaf = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode p = searchNode(word);
        if (p != null && p.leaf) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (searchNode(prefix) == null) {
            return false;
        } else {
            return true;
        }
    }

    private TrieNode searchNode(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (p.children[index] != null) {
                p = p.children[index];
            } else {
                return null;
            }
        }

        if (p == root)
            return null;

        return p;
    }
}

