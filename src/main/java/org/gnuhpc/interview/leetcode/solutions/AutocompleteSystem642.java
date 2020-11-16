package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

/**
 * Copyright gnuhpc 2020/11/14
 */
public class AutocompleteSystem642 {

    // 字典树
    private final Trie root;
    // 记录前一结点
    private Trie pre;
    // 记录历史字符串
    private StringBuffer sb;

    public AutocompleteSystem642(String[] sentences, int[] times) {
        this.root = new Trie(3);
        this.pre = root;
        sb = new StringBuffer();

        for (int i = 0; i < sentences.length; i++) {
            root.insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new LinkedList<>();
        // 终止
        if (c == '#') {
            // 更新历史记录
            saveHistory(sb.toString());
            // 清空状态
            pre = root;
            sb = new StringBuffer();
            return res;
        }

        // 记录历史记录
        sb.append(c);

        // 更新结点
        if (pre != null) pre = c == ' ' ? pre.children[26] : pre.children[c - 'a'];
        // 搜索其后所有值
        if (pre != null) pre.search(res);

        return res;
    }

    private void saveHistory(String s) {
        root.insert(s, 1);
    }
}

class Trie {
    // 只有结尾结点才有的属性
    boolean isEnding;
    int count;
    String s;

    Trie[] children = new Trie[27];
    // 最小堆保存最大的k个
    int k;
    PriorityQueue<Trie> queue;

    Trie(int k) {
        this.k = k;
        // 最小堆
        this.queue = new PriorityQueue<>((a, b) -> a.count == b.count ? b.s.compareTo(a.s) : a.count - b.count);
        // 最后一位存放空格
        this.children = new Trie[27];
    }

    public void insert(String word, int val) {
        if (word == null || word.isEmpty()) return;

        Trie temp = this;
        // 记录路径上的结点
        List<Trie> path = new LinkedList<>();
        for (char c : word.toCharArray()) {
            int idx = c == ' ' ? 26 : (c - 'a');
            if (temp.children[idx] == null) temp.children[idx] = new Trie(k);

            temp = temp.children[idx];
            path.add(temp);
        }
        // 结尾字符标记及计数更新
        temp.isEnding = true;
        temp.count += val;
        temp.s = word;

        // 关联终止节点到路径上每个结点
        for (Trie cur : path) {
            // 移除老的值
            cur.queue.remove(temp);
            // 加入新的值
            cur.queue.add(temp);
            // 大于k个，将最小的弹出
            while (cur.queue.size() > k) cur.queue.poll();
        }
    }

    public void search(List<String> res) {
        List<Trie> temp = new ArrayList<>();
        // 加入堆中元素
        while (!queue.isEmpty()) {
            Trie trie = queue.poll();
            temp.add(trie);
            res.add(trie.s);
        }
        queue.addAll(temp);
        Collections.reverse(res);
    }
}
