package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/11/26
 */
public class Construct427 {
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public Node construct(int[][] grid) {
        return subConstruct(grid, 0, 0, grid.length);
    }

    private Node subConstruct(int[][] grid, int i, int j, int length) {
        if (length == 1) return new Node(grid[i][j] == 1, true);
        boolean mark = true;
        int num = grid[i][j];
        for (int a = i; a < i + length; ++a) {
            for (int b = j; b < j + length; ++b) {
                if (num != grid[a][b]) {
                    mark = false;
                    break;
                }
            }
        }
        if (mark) return new Node(grid[i][j] == 1, true);
        Node now = new Node(true, false);
        now.topLeft = subConstruct(grid, i, j, length / 2);
        now.topRight = subConstruct(grid, i, j + length / 2, length / 2);
        now.bottomLeft = subConstruct(grid, i + length / 2, j, length / 2);
        now.bottomRight = subConstruct(grid, i + length / 2, j + length / 2, length / 2);
        return now;
    }

}
