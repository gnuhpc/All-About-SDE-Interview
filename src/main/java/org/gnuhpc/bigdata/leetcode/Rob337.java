package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

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
     优化：dfs + 记忆化搜索
     */

    Map<TreeNode,Integer> mapInclude = new HashMap<>();
    Map<TreeNode,Integer> mapExclude = new HashMap<>();
    public int rob2(TreeNode root) {
        int robIncludeRes=rob(root,true);
        int robExcludeRes=rob(root, false);

        return Math.max(robIncludeRes,robExcludeRes);
    }

    public int rob(TreeNode node,boolean isInclude){
        if(node==null){
            return 0;
        }
        if(isInclude){
            if(mapInclude.get(node)!=null){
                return mapInclude.get(node);
            }
            int rob=node.val+rob(node.left,false)+
                    rob(node.right,false);
            mapInclude.put(node, rob);
            return rob;
        }
        else{
            if(mapExclude.get(node)!=null){
                return mapExclude.get(node);
            }
            int leftMax=Math.max(rob(node.left,true),
                    rob(node.left,false));
            int rightMax=Math.max(rob(node.right,true),
                    rob(node.right,false));
            int rob=(leftMax+rightMax);
            mapExclude.put(node, rob);
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

    //add by tina:TODO memo search，需要想清楚状态方程，容易错
    private HashMap<TreeNode,Integer> map;
    public int rob5(TreeNode root) {
        if(root == null ) return 0;
        map = new HashMap<>();
        return memoSearch( root);
    }

    public int memoSearch(TreeNode root){
        if(root == null) return 0;
        if(map.containsKey(root)) return map.get(root);
        int res = 0;
        res = Math.max(
                root.val+
                (root.left==null?0:memoSearch(root.left.left))+
                (root.left==null?0:memoSearch(root.left.right))+
                (root.right==null?0:memoSearch(root.right.left))+
                (root.right==null?0:memoSearch(root.right.right)),
                memoSearch(root.left)+memoSearch(root.right));
        map.put(root,res);//第一版写成6行数据作比较了，严重跑偏
        return res;
    }



}
