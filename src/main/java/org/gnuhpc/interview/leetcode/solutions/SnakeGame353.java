package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;

/**
 * Copyright gnuhpc 2019/10/7
 */
/*
I use a linkedlist to mimic the snake movement. Every time we move one step forward, we get a new x and y.
We have three situations:
(1) The new position is out of the boundary, return -1
(2) The new position reach the end of the snake body, remember to delete the oldest point in the linkedlist first!
(3) the new position is the food, then we just need to add the deleted point back to the linkedlist. The list will be one point longer then before.
We keep an foodIndex to reserve the score and the index for food array.
*/

/*
通过一个list来维护蛇的身体，如果没吃到豆子就加上头去掉尾巴，吃到了就不去掉尾巴。
 */

public class SnakeGame353 {
    /**
     * Initialize your data structure here.
     *
     * @param width - screen width
     * @param height - screen height
     * @param food - A list of food positions
     * E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
     */
    int[][] food;
    int width;
    int height;
    int foodIndex;
    LinkedList<int[]> track;

    public SnakeGame353(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.foodIndex = 0;
        this.food = food;
        track = new LinkedList<>();
        int[] n = {0, 0};
        track.add(n);
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        int x = track.get(0)[0];
        int y = track.get(0)[1];
        int[] newpoint = new int[2];
        if (direction.equals("U")) {
            x = x - 1;
            y = y;
        } else if (direction.equals("L")) {
            x = x;
            y = y - 1;
        } else if (direction.equals("R")) {
            x = x;
            y = y + 1;
        } else {
            x = x + 1;
            y = y;
        }
        if (x < 0 || x >= height || y < 0 || y >= width) return -1;
        newpoint[0] = x;
        newpoint[1] = y;
        int[] todelete = track.get(track.size() - 1);
        track.remove(track.size() - 1);
        if (isdead(x, y)) return -1;
        track.add(0, newpoint);
        if (food.length > foodIndex && x == food[foodIndex][0] && y == food[foodIndex][1]) {
            track.add(todelete);
            foodIndex++;
            return foodIndex;
        }
        return foodIndex;
    }

    //to test whether it will reach the tails
    public boolean isdead(int x, int y) {
        for (int i = track.size() - 1; i >= 0; i--) {
            int[] t = track.get(i);
            if (t[0] == x && t[1] == y) return true;
        }
        return false;
    }
}
