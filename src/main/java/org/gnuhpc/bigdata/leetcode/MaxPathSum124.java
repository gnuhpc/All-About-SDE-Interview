package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

//http://www.cnblogs.com/grandyang/p/4280120.html
/*
这道求二叉树的最大路径和是一道蛮有难度的题，难就难在起始位置和结束位置可以为任意位置，我当然是又不会了，于是上网看看大神们的解法，像这种类似数的遍历的题，一般来说都需要用DFS来求解，我们先来看一个简单的例子：

    4
   / \
  11 13
 / \
7   2
由于这是一个很简单的例子，我们很容易就能找到最长路径为7-11-4-13，那么怎么用递归来找出正确的路径和呢？
根据以往的经验，树的递归解法一般都是递归到叶节点，然后开始边处理边回溯到根节点。那么我们就假设此时已经递归到结点7了，
那么其没有左右子节点，所以如果以结点7为根结点的子树最大路径和就是7。
然后回溯到结点11，如果以结点11为根结点的子树，我们知道最大路径和为7+11+2=20。
但是当回溯到结点4的时候，对于结点11来说，就不能同时取两条路径了，只能取左路径，或者是右路径，
所以当根结点是4的时候，那么结点11只能取其左子结点7，因为7大于2。
所以，对于每个结点来说，我们要知道经过其左子结点的path之和大还是经过右子节点的path之和大。
那么我们的递归函数返回值就可以定义为以当前结点为根结点，到叶节点的最大路径之和，然后全局路径最大值放在参数中，用结果max来表示。

在递归函数中，如果当前结点不存在，那么直接返回0。
否则就分别对其左右子节点调用递归函数，
由于路径和有可能为负数，而我们当然不希望加上负的路径和，所以我们和0相比，取较大的那个，就是要么不加，加就要加正数。
然后我们来更新全局最大值结果max，就是以左子结点为终点的最大path之和加上以右子结点为终点的最大path之和，还要加上当前结点值，这样就组成了一个条完整的路径。
而我们返回值是取left和right中的较大值加上当前结点值，因为我们返回值的定义是以当前结点为终点的path之和，
所以只能取left和right中较大的那个值，而不是两个都要.

 */
public class MaxPathSum124 {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }
    //Path pass parent node only once
    //at every node, either add its left or right path or nth for n
    public int helper(TreeNode root){
        if(root == null) return 0;
        int sum1 = Math.max(0, helper(root.left));
        int sum2 = Math.max(0, helper(root.right));
        max = Math.max(max, sum1 + sum2 + root.val);
        return root.val + Math.max(sum1, sum2);
    }

}
