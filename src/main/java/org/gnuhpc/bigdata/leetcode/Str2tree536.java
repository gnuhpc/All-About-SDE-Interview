package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.junit.Test;

public class Str2tree536 {
    public TreeNode str2tree(String s) {
        if (s == null || s.isEmpty()) return null;

        char[] arr = s.toCharArray();
        int i = 0;

        boolean isLeft = true;

        TreeNode root = null;
        StringBuilder sb = new StringBuilder();
        while (i<arr.length){
            char c = arr[i];
            if (c == '-'|| (c >= '0' && c <= '9')) {
                sb.append(c);
            }

            if (c =='('){
                if(sb.length() != 0) {
                    root = new TreeNode(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder();
                }
                int toMatched = 1, end = i+1;
                while (end<arr.length && toMatched>0){
                    char cc = s.charAt(end);
                    if (cc == '(') toMatched++;
                    else if (cc == ')') toMatched--;
                    end++;
                }
                if (isLeft) {
                    root.left = str2tree(s.substring(i + 1, end - 1));
                } else {
                    root.right = str2tree(s.substring(i + 1, end - 1));
                }

                isLeft =!isLeft;

                i = end-1;
            }
            i++;
        }
        if(sb.length() != 0) return new TreeNode(Integer.parseInt(sb.toString()));

        return root;
    }

    /*
    Method2: 使用全局变量进行迭代的位置更迭
     */
    int idx = -1;
    public TreeNode str2tree2(String s) {
        if(s.equals(""))
            return null;
        TreeNode node = null;
        while(++idx < s.length()) {
            char c = s.charAt(idx);
            if(Character.isDigit(c) || c == '-') {
                node = new TreeNode(getInt(s));
            } else if(c == '(') {
                if(node.left == null)
                    node.left = str2tree(s);
                else
                    node.right = str2tree(s);
            } else
                return node;
        }
        return node;
    }

    public int getInt(String s) {
        int ret = 0;
        int sign = 1;
        char c = s.charAt(idx);
        if(c == '-')
            sign = -1;
        else
            ret = c - '0';
        while(++idx < s.length() && Character.isDigit(s.charAt(idx)))
            ret = ret * 10 + s.charAt(idx) - '0';
        --idx;
        return ret * sign;
    }


    @Test
    public void test(){
        System.out.println(str2tree("51(232)(434)"));
    }
}
