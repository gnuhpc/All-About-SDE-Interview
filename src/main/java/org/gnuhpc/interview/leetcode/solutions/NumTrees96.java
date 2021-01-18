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
    int[][] memo;

    public int numTrees(int n) {

        if(n <= 0)
            return 0;

        memo  = new int[n+2][n+2];//为什么加2是因为按照从0开始的原则首先要加1，然后index+1还可能越界就再加1
        return numTrees(1, n);
    }

    private int numTrees(int start, int end){
        if(memo[start][end]!=0) return memo[start][end];
        if(start >= end){
            memo[start][end] = 1;
            return 1;
        }

        int result = 0;
        for(int index = start; index <= end; index++){
            int numLeftTrees = numTrees(start, index - 1);
            int numRightTrees = numTrees(index + 1, end);
            result += numLeftTrees * numRightTrees;
        }

        memo[start][end] = result;
        return result;
    }

}
