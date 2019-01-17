package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.bigdata.leetcode.utils.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PathSum113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        // 处理两个特殊情况
        if (root == null)
            return res;

        if (root.left == null && root.right == null) {
            if(root.val == sum) res.add(new ArrayList<Integer>(){{add(root.val);}});
            return res;
        }

        robot(root, sum, 0, new ArrayList<>(), res);

        return res;
    }

    private void robot(TreeNode root, int sum, int cur,
                       ArrayList<Integer> temp, List<List<Integer>> res) {
        if (root == null) return;
        temp.add(root.val);
        cur +=root.val;

        if (root.left == null && root.right == null) {
            if (cur == sum){
                res.add(new ArrayList<>(temp));
            }
            //返回之前统一回溯,不管匹配成功与否
            temp.remove(temp.size()-1);
            return;
        }

        robot(root.left,sum,cur,temp,res);
        robot(root.right,sum,cur,temp,res);

        // 注意，此时左右子节点都做完了，就要开始向上回溯了，这个节点也可以退出遍历过程了！ 回溯！！
        temp.remove(temp.size()-1);
    }

    @Test
    public void test(){
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1});
        System.out.println(pathSum(root,22));
    }
}
