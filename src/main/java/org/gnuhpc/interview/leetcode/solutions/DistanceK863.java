package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.*;

public class DistanceK863 {
    private final List<Integer> list = new ArrayList<>();
    private int k;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (root == null) {
            return Collections.emptyList();
        }
        this.k = k;
        dfs(root, target);
        return list;
    }

    /**
     * @param root   起始点
     * @param target 终止点
     * @return 返回两点之间的距离（边的个数）
     */
    private int dfs(TreeNode root, TreeNode target) {
        if (root == null) {
            return -1;
        }
        if (root == target) {
            addNodes(root, k);
            return 1;
        }
        int l = dfs(root.left, target);
        int r = dfs(root.right, target);
        if (l > 0 && l < k) {
            //在左分支找到了，但是从root.left到target不足k，那么就是在右边找
            addNodes(root.right, k - l - 1);
            return l + 1;
        }
        if (r > 0 && r < k) {
            //在右分支找到了，但是从root.right到target不足k，那么就是在左边找
            addNodes(root.left, k - r - 1);
            return r + 1;
        }
        if (l == k || r == k) {
            addNodes(root, 0);
            return k + 1;
        }
        return -1;
    }

    /**
     * 将与根节点距离为distance的子节点添加到结果集中
     *
     * @param root     根节点
     * @param distance 与根节点的距离
     */
    private void addNodes(TreeNode root, int distance) {
        if (root == null) {
            return;
        }
        if (distance == 0) {
            list.add(root.val);
            return;
        }
        addNodes(root.left, distance - 1);
        addNodes(root.right, distance - 1);
    }

    /*
    Method2: BFS
     */

    Map<TreeNode, TreeNode> parent;

    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        parent = new HashMap();
        // 1. DFS to get `node => parent` map
        preorder(root, null);

        Queue<TreeNode> queue = new LinkedList();
        queue.add(null);    // level 分界线
        queue.add(target);

        Set<TreeNode> seen = new HashSet();
        seen.add(target);
        seen.add(null); // 不访问 null node

        int dist = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // null 为层序分界线
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n : queue) {
                        ans.add(n.val);
                    }
                    return ans;
                }
                queue.offer(null); // 作为层的分界线
                dist++;
            } else {
                // left
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                // right
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                // up
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    //获取每个节点的父节点
    public void preorder(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            preorder(node.left, node);
            preorder(node.right, node);
        }
    }


}
