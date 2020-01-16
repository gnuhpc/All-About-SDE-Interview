package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.junit.Test;

/*
根据题意，最大路径和可能出现在：
左子树中
右子树中
包含根节点与左右子树
我们的思路是递归从bottom向topreturn的过程中，记录左子树和右子树中路径更大的那个，并向父节点提供当前节点和子树组成的最大值。
递归设计：
返回值：(root.val) + max(left, right) 即此节点与左右子树最大值之和，较差的解直接被舍弃，不会再被用到。
需要注意的是，若计算结果tmp <= 0，意味着对根节点有负贡献，不会在任何情况选这条路（父节点中止），因此返回0。
递归终止条件：越过叶子节点，返回0；
记录最大值：当前节点最大值 = root.val + left + right。
最终返回所有路径中的全局最大值即可。

作者：jyd
链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/binary-tree-maximum-path-sum-bottom-up-di-gui-jie-/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MaxPathSum124 {

    private int ret = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        /**
         对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
         1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
         2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
         **/
        getMax(root);
        return ret;
    }

    private int getMax(TreeNode r) {
        if (r == null) return 0;
        int left = Math.max(0, getMax(r.left)); // 如果子树路径和为负则应当置0表示最大路径不包含子树
        int right = Math.max(0, getMax(r.right));
        //不断更新max为val+left+right. 但每次返回的时候只返回 root.val + max(left,right). 这个实在是太巧妙了。
        ret = Math.max(ret, r.val + left + right); // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        //去求左子树能返回的最大值，右子树能返回的最大值，选一个较大的
        return Math.max(left, right) + r.val;
    }

    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{-1, null, 2});
        System.out.println(maxPathSum(root));
    }

}
