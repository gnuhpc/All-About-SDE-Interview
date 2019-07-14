package org.gnuhpc.bigdata.leetcode;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

//Trie解法，当然也可以直接使用startwith暴力，https://www.jianshu.com/p/c2d2b163df0f
public class MapSum677 {
    class TrieNode {
        private static final int ALPHABETSIZE = 26;

        //实现hash结构时采用
        public String character;
        public int val;

        public TrieNode[] children;

        public TrieNode(String character, int val) {
            this.character = character;
            this.children = new TrieNode[ALPHABETSIZE];
            this.val = val;
        }

        public TrieNode() {
            this.character = "";
            this.children = new TrieNode[ALPHABETSIZE];
        }
    }


    private TrieNode root;
    //还有一种解法是不沿途存储最后的sum，只存每个token最后一个字符node的val，然后sum的时候找到将值加起来
    //不沿途存更省内存
    private Set<String> tokenSet; //根据题意，后边如果出现同样的token，则采用val替换的模式，否则则是叠加

    /** Initialize your data structure here. */
    public MapSum677() {
        root = new TrieNode();
        tokenSet = new HashSet<>();
    }

    public void insert(String key, int val) {
        boolean isReplace = tokenSet.contains(key);

        char[] keyArray = key.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < keyArray.length; i++) {
            int idx = keyArray[i]-'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode(String.valueOf(keyArray[i]),val);
            } else {
                TrieNode n = node.children[idx];
                if (isReplace) {
                    n.val = val;
                } else {
                    n.val += val;
                }
            }
            node = node.children[idx];
        }

        tokenSet.add(key);
    }

    public int sum(String prefix) {
        TrieNode p = root;

        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (p.children[idx] != null){
                p = p.children[idx];
            } else {
                return 0;
            }

        }

        return p.val;
    }

    @Test
    public void test(){
        insert("aa",3);
        System.out.println(sum("a"));
        insert("aa",2);
        System.out.println(sum("a"));
    }
}
