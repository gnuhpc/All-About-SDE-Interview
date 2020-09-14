package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/9/12
 */
public class MinKnightMoves1197 {
    public int minKnightMoves(int x, int y) {
        boolean[][] visited = new boolean[601][601]; // 坐标映射
        Queue<Node> queue = new LinkedList<>();
        int[][] dir = {{1, 2}, {2, 1}, {-1, 2}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {2, -1}};
        Node root = new Node(0, 0, 0);
        Node desNode = new Node(x, y, Integer.MAX_VALUE);
        queue.add(root);
        int abs = root.getDis(desNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.x == x && node.y == y) {
                return node.step;
            }
            if (visited[node.x + 300][node.y + 300]) {
                continue;
            }
            visited[node.x + 300][node.y + 300] = true;
            int dis = node.getDis(desNode);
            for (int i = 0; i < 8; i++) {
                int tmpX = node.x + dir[i][0];
                int tmpY = node.y + dir[i][1];
                Node tmpNode = new Node(tmpX, tmpY, Integer.MAX_VALUE);
                // 贪心算法，减枝, 注意如果abs本来就小于4，那就统统加进去看，因为1步到不了终点
                if (dis > tmpNode.getDis(desNode) || abs < 4) {
                    tmpNode.step = node.step + 1;
                    queue.add(tmpNode);
                }
            }
        }
        return 0;
    }


    class Node {
        public int x;
        public int y;
        public int step;

        Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }

        public int getDis(Node o) {
            return Math.abs(o.x - x) + Math.abs(o.y - y);
        }
    }
}
