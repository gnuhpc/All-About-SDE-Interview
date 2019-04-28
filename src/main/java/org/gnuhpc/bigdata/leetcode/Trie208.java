package org.gnuhpc.bigdata.leetcode;
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

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


class Trie {

    private class Node{
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }
    private Node root;


    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for(int i = 0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        if(!cur.isWord){
            cur.isWord = true;
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for(int i = 0;i<word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }else{
                cur = cur.next.get(c);
            }
        }
        return cur.isWord;

    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for(int i = 0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }else{
                cur = cur.next.get(c);
            }
        }
        return true;
    }
}
