package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/*
思路：假设n个节点存在二叉排序树的个数是G(n)，1为根节点，2为根节点，...，
n为根节点，当1为根节点时，其左子树节点个数为0，右子树节点个数为n-1，
同理当2为根节点时，其左子树节点个数为1，右子树节点为n-2，
所以可得G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)
 */
public class NumTrees96 {
    public int numTrees(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 1);
        return numTrees(n, map);
    }

    private int numTrees(int n, Map<Integer, Integer> map) {
        // check memory
        if (map.containsKey(n)) return map.get(n);
        // recursion
        int sum = 0;
        for (int i = 1; i <= n; i++)
            sum += numTrees(i - 1, map) * numTrees(n - i, map);
        map.put(n, sum);
        return sum;
    }
}
