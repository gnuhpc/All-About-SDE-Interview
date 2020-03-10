package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

/*
没什么好说的，最简单的BFS，上BFS模板
 */
public class LevelOrder429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        bfs(root, res);
        return res;

    }

    private void bfs(Node root, List<List<Integer>> res) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> tmpList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node n = queue.poll();
                tmpList.add(n.val);
                for (Node nextLNode : n.children) {
                    queue.offer(nextLNode);
                }
            }

            res.add(tmpList);
        }
    }
}
