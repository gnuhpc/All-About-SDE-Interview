package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class IsSymmetric101 {
    /*
    Method 1: Recursion
     */
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }

    /*
    Method 2: iteration
     */

    public boolean isSymmetric2(TreeNode root) {
        if (root == null)
            return true;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();
            if (node1 == null && node2 == null)
                continue;
            if (node1 == null || node2 == null)
                return false;
            if (node1.val != node2.val)
                return false;
            stack.push(node1.left);
            stack.push(node2.right);
            stack.push(node1.right);
            stack.push(node2.left);
        }
        return true;
    }

    /*
    Method3 : using queue  (Fastest)
     */
    public boolean isSymmetric3(TreeNode root) {
        if(root==null) return true;
        Queue<TreeNode> q = new LinkedList<>();

        if(!insertNodes(root.left, root.right, q)) return false;
        while(!q.isEmpty()){
            TreeNode n1 = q.poll();
            TreeNode n2 = q.poll();
            if(!insertNodes(n1.left, n2.right, q) || !insertNodes(n1.right, n2.left, q)) return false;
        }

        return true;
    }

    public boolean insertNodes(TreeNode n1, TreeNode n2, Queue<TreeNode> q){
        if(n1==null && n2==null) return true;
        if(n1==null || n2==null || n1.val!=n2.val) return false;
        q.add(n1);
        q.add(n2);
        return true;
    }

    /*
    Method 4: level traversal
     */

    public boolean isSymmetric4(TreeNode root) {
        // 11:25pm
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> l = new ArrayList<Integer>();
            for (int i=0; i<size; i++) {
                TreeNode curr = q.poll();
                if (curr == null) {
                    l.add(null);
                } else {
                    l.add(curr.val);
                    q.offer(curr.left);
                    q.offer(curr.right);
                }
            }
            if (!isSymmetricList(l)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSymmetricList(List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
