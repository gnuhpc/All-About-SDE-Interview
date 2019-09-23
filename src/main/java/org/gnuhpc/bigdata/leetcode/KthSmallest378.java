package org.gnuhpc.bigdata.leetcode;

import scala.Tuple3;

import java.util.PriorityQueue;

public class KthSmallest378 {
    /*
    将每一列视为一个有序链表，就和mergeklinkedlist题目一样了。
    minheap method
    Build a minHeap of elements from the first row.
    Do the following operations k-1 times :
    Every time when you poll out the find(Top Element in Heap),
    you need to know the row number and column number of that element(so we can create a tuple class here)
    replace that find with the next element from the same column.
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
        for(int i = 0; i < k-1; i++) {
            Tuple t = pq.poll();
            if(t.x == n-1) continue;
            pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
        }
        return pq.poll().val;
    }

    /*
    Binary search Method:

    The key point for any binary search is to figure out the "Search Space".
    For me, I think there are two kind of "Search Space"
    -- index and range(the range from the smallest number to the biggest number).
    Most usually, when the array is sorted in one direction, we can use index as "search space"
    when the array is unsorted and we are going to find a specific number, we can use "range".
    The reason why we did not use index as "search space" for this problem is the matrix is sorted in two directions, we can not find a linear way to distanceMap the number and its index.
     */

    public int kthSmallest2(int[][] matrix, int k) {
        int len = matrix.length;
        //定义范围
        int low = matrix[0][0], high = matrix[len - 1][len - 1];
        while (low + 1< high) {  // 注意是小于等于
            int mid = low + (high - low) / 2;
            int count = helper(matrix, mid);
            // 如果这个count小于k，说明mid猜小了
            if (count < k)
                low = mid;
            else
                // 否则说明mid猜大了
                high = mid;
        }

        //这里一定要是大于等于，因为如果只是等于的话，如下例子就有问题
        //1 1 1 1 1 1 2 3   k = 1
        //          l h
        // helper返回的是6，但是由于有重复的，low对应的1就是要返回的。
        if (helper(matrix,low) >=k) return low;
        else return high;
    }

    private static int helper(int[][] matrix, int mid) {
        //从左下角的原因起是因为如果mid小于这个则一行都可以忽略并往上找
        int i = matrix.length - 1, j = 0;
        int res = 0;
        //逐行查找比mid小的 = count ， 这个也可以改为二分
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] > mid)
                i--;
            else {
                res += i + 1;
                j++;
            }
        }
        return res;
    }
}

class Tuple implements Comparable<Tuple> {
    int x, y, val;
    public Tuple (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}
