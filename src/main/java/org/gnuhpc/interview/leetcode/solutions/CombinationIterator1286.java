package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/5/12
 */
public class CombinationIterator1286 {

    private int len;
    private char[] characters;
    private List<String> res;
    private int index;

    //先选定前置位 在依次选定后置位 全排列问题
    public CombinationIterator1286(String characters, int combinationLength) {
        this.characters = characters.toCharArray();
        this.len = combinationLength;
        this.res = new ArrayList<>();
        this.index = 0;
        dfs(new ArrayList<>(), 0);
    }

    private void dfs(List<Character> temp, int index) {
        if (temp.size() == len) {
            StringBuilder sb = new StringBuilder();
            for (char c : temp) {
                sb.append(c);
            }
            res.add(sb.toString());
            return;
        }

        for (int i = index; i < characters.length; i++) {
            //从index开始 依次往后添加元素
            temp.add(characters[i]);
            //回溯下一个位置
            dfs(temp, i + 1);
            //移除最后一个位置 寻找当前节点的下一种可能
            temp.remove(temp.size() - 1);
        }
    }

    public String next() {
        return res.get(index++);
    }

    public boolean hasNext() {
        return !(index == res.size());
    }
}
