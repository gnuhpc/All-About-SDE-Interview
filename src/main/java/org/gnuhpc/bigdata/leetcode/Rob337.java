package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.HashMap;

public class Rob337 {
    /*
    DFS1
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        return Math.max(robInclude(root), robExclude(root));
    }

    public int robInclude(TreeNode node) {
        if(node == null) return 0;
        return robExclude(node.left) + robExclude(node.right) + node.val;
    }

    public int robExclude(TreeNode node) {
        if(node == null) return 0;
        return rob(node.left) + rob(node.right);
    }

    /*
    DFS2
     */
    public int rob2(TreeNode root) {
        return Math.max(dfs(root,true), dfs(root,false));
    }

    public int dfs(TreeNode node, boolean canRob){
        if(node==null)
            return 0;

        if (node.left == null && node.right == null)
            return canRob ? node.val : 0;

        if(canRob)
            return node.val + dfs(node.left,false) + dfs(node.right,false);
        else
            return Math.max(dfs(node.left,true), dfs(node.left,false)) +
                    Math.max(dfs(node.right,true), dfs(node.right,false));
    }

    /*
    dfs + 记忆化搜索
     */

    HashMap<TreeNode,Integer> mapT= new HashMap<>();
    HashMap<TreeNode,Integer> mapF= new HashMap<>();
    public int rob3(TreeNode root) {
        int robTrue=rob(root,true);
        int robFalse=rob(root, false);

        return Math.max(robTrue,robFalse);
    }

    public int rob(TreeNode node,boolean canRob){
        if(node==null){
            return 0;
        }
        if(canRob){
            if(mapT.get(node)!=null){
                return mapT.get(node);
            }
            int rob=node.val+rob(node.left,false)+
                    rob(node.right,false);
            mapT.put(node, rob);
            return rob;
        }
        else{
            if(mapF.get(node)!=null){
                return mapF.get(node);
            }
            int leftMax=Math.max(rob(node.left,true),
                    rob(node.left,false));
            int rightMax=Math.max(rob(node.right,true),
                    rob(node.right,false));
            int rob=(leftMax+rightMax);
            mapF.put(node, rob);
            return rob;
        }
    }


    /*

    动态规划
     */
    public int rob4(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    // res[0] 选择当前节点的最大值， res[1] 不选择当前节点的最大值
    private int[] dfs(TreeNode node) {
        int[] res = {0, 0};
        if (node != null) {
            int[] left = dfs(node.left);
            int[] right = dfs(node.right);
            //选择当前结点则必可以选择 不选l1和r1的
            res[0] = node.val + left[1] + right[1];
            res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        }
        return res;
    }



}
