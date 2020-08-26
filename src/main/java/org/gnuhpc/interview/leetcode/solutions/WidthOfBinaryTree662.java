package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.tree.basicimpl.TreeNode;

import java.util.*;

public class WidthOfBinaryTree662 {
    /*
    Method1 : BFS
     */
    int max = Integer.MIN_VALUE;

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        if (root.right == null && root.left == null) return 1;

        traversalTreeByLevel(root);

        return max;
    }

    private void traversalTreeByLevel(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        while (!q.isEmpty()) {
            List<TreeNode> temp = new ArrayList<>();
            int size = q.size();

            for (int i = 0; i < size; i++) {
                temp.add(q.poll());
            }
            List<TreeNode> trimedList = getTrimNull(temp);
            if (trimedList.size() == 0) break;
            max = Math.max(max, trimedList.size());
            for (int i = 0; i < trimedList.size(); i++) {
                TreeNode n = trimedList.get(i);
                if (n != null) {
                    q.offer(n.left);
                    q.offer(n.right);
                } else {
                    q.offer(null);
                    q.offer(null);
                }
            }
        }
    }

    private List<TreeNode> getTrimNull(List<TreeNode> list) {
        List<TreeNode> res = new ArrayList<>();
        if (list == null) return res;
        int left = 0, right = list.size() - 1;

        while (left <= right && list.get(left) == null) {
            left++;
        }
        while (left <= right && list.get(right) == null) {
            right--;
        }

        return list.subList(left, right + 1);
    }

    /*
    Method2: DFS
    https://leetcode.com/articles/maximum-width-of-binary-tree/
     */

    int ans;
    Map<Integer, Integer> left;

    public int widthOfBinaryTree2(TreeNode root) {
        ans = 0;
        left = new HashMap<>();
        dfs(root, 0, 0);
        return ans;
    }

    public void dfs(TreeNode root, int depth, int pos) {
        if (root == null) return;
        left.putIfAbsent(depth, pos);
        ans = Math.max(ans, pos - left.get(depth) + 1);
        dfs(root.left, depth + 1, 2 * pos);
        dfs(root.right, depth + 1, 2 * pos + 1);
    }


    /*
    Method3 : BFS
    https://leetcode.com/articles/maximum-width-of-binary-tree/
     */

    public int widthOfBinaryTree3(TreeNode root) {
        Queue<AnnotatedNode> queue = new LinkedList<>();
        queue.add(new AnnotatedNode(root, 0, 0));
        int curDepth = 0, left = 0, ans = 0;
        while (!queue.isEmpty()) {
            AnnotatedNode n = queue.poll();
            if (n.node != null) {
                queue.add(new AnnotatedNode(n.node.left, n.depth + 1, n.pos * 2));
                queue.add(new AnnotatedNode(n.node.right, n.depth + 1, n.pos * 2 + 1));
                if (curDepth != n.depth) {
                    curDepth = n.depth;
                    left = n.pos;
                }
                ans = Math.max(ans, n.pos - left + 1);
            }
        }
        return ans;
    }
}

class AnnotatedNode {
    TreeNode node;
    int depth, pos;

    AnnotatedNode(TreeNode n, int d, int p) {
        node = n;
        depth = d;
        pos = p;
    }
}

