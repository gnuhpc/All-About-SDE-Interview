package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright gnuhpc 2020/9/12
 */

/*
思路是DFS。这道题既然是模拟扫地机器人，那么DFS其实是蛮直观的。可以用BFS做吗？不能。因为BFS做是没法回到原点的。
这道题大部分的实现都还是比较直观的，需要注意以下几点

需要一个hashset记录访问过的坐标
给机器人定义的移动方向是需要有顺序的，要不然是顺时针要不然是逆时针。我这里给的是顺时针的做法，上右下左。当机器人在某一个方向走到头的时候，需要让它原地180度掉头，回到初始位置，再去扫描下一个位置



作者：shurui91
链接：https://leetcode-cn.com/problems/robot-room-cleaner/solution/java-dfs-by-shurui91/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class CleanRoom489 {
    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        boolean move();

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        void turnLeft();

        void turnRight();

        // Clean the current cell.
        void clean();
    }

    Set<Node> set = new HashSet<>();
    int[][] move = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public void cleanRoom(Robot robot) {
        backTrace(robot, 0, 0, 0);
    }

    public void backTrace(Robot robot, int x, int y, int d) {
        robot.clean();
        set.add(new Node(x, y));

        for (int i = 0; i < 4; i++) {
            int curD = (d + i) % 4;
            int nextX = x + move[curD][0];
            int nextY = y + move[curD][1];

            if (!set.contains(new Node(nextX, nextY)) && robot.move()) {
                backTrace(robot, nextX, nextY, curD);
                back(robot);
            }

            robot.turnRight();
        }
    }

    public void back(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }


    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int hashCode() {
            return x * 31 + y;
        }

        public boolean equals(Object o) {
            Node other = (Node) o;
            return x == other.x && y == other.y;
        }
    }


}
