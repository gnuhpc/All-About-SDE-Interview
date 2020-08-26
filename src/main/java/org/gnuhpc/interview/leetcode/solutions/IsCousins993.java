package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/3/1
 */
public class IsCousins993 {
    /*
    Method1: BFS
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return false;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int cnt = 0;
            for (int i = 0; i < size; i++) {
                TreeNode t = queue.poll();
                if (t.left != null && t.right != null) {
                    if (t.left.val == x && t.right.val == y) return false;
                    if (t.left.val == y && t.right.val == x) return false;
                }
                if (t.left != null) {
                    if (t.left.val == x || t.left.val == y) {
                        cnt++;
                    }
                    queue.add(t.left);
                }
                if (t.right != null) {
                    if (t.right.val == x || t.right.val == y) {
                        cnt++;
                    }
                    queue.add(t.right);
                }
            }
            if (cnt == 2) return true;
        }
        return false;
    }
    /*
    Method2: DFS

    思路

当且仅当一对节点深度相同而父节点不相同时，它们是堂兄弟节点。一种非常直接的方法就是通过某种方法求出每一个节点的深度与父节点。

算法

我们用深度优先搜索标记每一个节点，对于每一个节点 node，它的父亲为 par，深度为 d，我们将其记录到 Hashmap 中：parent[node.val] = par 且 depth[node.val] = d。

作者：LeetCode
链接：https://leetcode-cn.com/problems/cousins-in-binary-tree/solution/er-cha-shu-de-tang-xiong-di-jie-dian-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    Map<Integer, Integer> depth;
    Map<Integer, TreeNode> parent;

    public boolean isCousins2(TreeNode root, int x, int y) {
        depth = new HashMap();
        parent = new HashMap();
        dfs(root, null);
        return (depth.get(x) == depth.get(y) && parent.get(x) != parent.get(y));
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            depth.put(node.val, par != null ? 1 + depth.get(par.val) : 0);
            parent.put(node.val, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

}
