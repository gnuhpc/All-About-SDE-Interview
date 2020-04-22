package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeCreator;
import org.gnuhpc.interview.leetcode.utils.TreeNode;
import org.junit.Test;

import javax.jnlp.IntegrationService;
import java.util.*;

public class RightSideView199 {

    /*
        BFS
         */
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);

                if (i == size - 1) res.add(node.val);
            }
        }

        return res;
    }

    /*
    DFS1
     */
    List<Integer> res = new ArrayList<>();
    int index = 0;//要写入res的下标

    public List<Integer> rightSideView2(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == index) {   // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(index, root.val);
            index++; //这一层的写过了即使遍历到也不会再写了
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }

    //depth-value
    private Map<Integer, Integer> map = new HashMap<>();

    public List<Integer> rightSideView3(TreeNode root) {
        if (root == null) return res;

        dfs3(root, 0);

        for (int i = 0; map.containsKey(i); i++) {
            res.add(map.get(i));
        }

        return res;
    }

    private void dfs3(TreeNode root, int depth) {
        if (root == null) return;

        //这句话的位置在哪无所谓，因为不管是前序中序还是后序，右节点都是晚于左节点放置的(除了根节点)
        //而根节点由于没人和它重合，因此无所谓
        map.put(depth, root.val);

        dfs3(root.left, depth + 1);//交换这一句和下一句就得出从左边看的view...
        dfs3(root.right, depth + 1);
    }

    /*
    Method4: 分而治之
     */
    public List<Integer> rightSideView4(TreeNode root) {
        List<Integer> leftList = rightSideView(root.left);//左子树生成的列表
        List<Integer> rightList = rightSideView(root.right);//右子树生成的列表

        int max = Math.max(leftList.size(), rightList.size());//左右子树列表的最大长度
        List<Integer> list = new ArrayList<>(max);//最终合成的列表
        list.add(root.val);//先把当前节点加入最终列表
        for (int i = 0; i < max; i++) {//优先加入右子树的列表元素，若右子树加完了，则开始加入左子树列表
            if (i < rightList.size()) {
                list.add(rightList.get(i));
            } else {
                list.add(leftList.get(i));
            }
        }
        return list;
    }


    @Test
    public void test() {
        TreeNode root = TreeCreator.createTreeByLevel(new Integer[]{1, 2, 3, null, 5, null, 4});
        System.out.println(rightSideView(root));
    }
}
