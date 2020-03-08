package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class PartitionDisjoint915 {
    //Method: Force-brute 1496ms passed
    public int partitionDisjoint(int[] A) {
        if (A == null || A.length <= 1) return -1;

        int res = Integer.MAX_VALUE;
        for (int pos = 0; pos < A.length - 1; pos++) {
            int maxLeft = getMaxLeft(A, pos);
            int minRight = getMinRight(A, pos);
            if (maxLeft <= minRight) {
                return pos + 1;
            }
        }

        return res + 1;

    }

    private int getMinRight(int[] A, int pos) {
        int res = Integer.MAX_VALUE;
        for (int i = pos + 1; i < A.length; i++) {
            if (A[i] < res) res = A[i];
        }

        return res;
    }

    private int getMaxLeft(int[] A, int pos) {
        int res = Integer.MIN_VALUE;

        for (int i = 0; i <= pos; i++) {
            if (A[i] > res) res = A[i];
        }

        return res;
    }


    //Method2: 猜测排除法
    public int partitionDisjoint2(int[] A) {
        if (A == null || A.length <= 1) return -1;

        Queue<Integer> pq = new PriorityQueue<>();
        pq.addAll(Arrays.stream(A).boxed().collect(Collectors.toList()));
        //各自先猜一个数字
        int maxLeft = A[0];
        int minRight = pq.poll();

        //目标maxLeft<=minRight
        for (int pos = 0; pos < A.length - 1; pos++) {

            //求左边最大
            if (A[pos] > maxLeft)
                maxLeft = A[pos];

            //如果游标指向的数值已经大于右边最小但是还没有出现maxLeft<=minRight
            //则说明这个数值一定不会是右边最小,排除法
            if (A[pos] > minRight) {
                pq.remove(A[pos]);
            }

            //如果游标指向的数值已经等于右边最小但是还没有出现maxLeft<=minRight
            //则右边最小取下一个
            if (A[pos] == minRight) {
                minRight = pq.poll();
            }

            //判断是否出现目标情况
            if (maxLeft <= minRight) {
                return pos + 1;
            }
        }

        //Never run this line
        return -1;
    }


    //Method3 : 局部最大， 分为两个区域： 一定包含在left中的，不一定包含在left中的
    /*
    从左向右进行扫描，记leftMax为已经确定需要包括在left数组中的数的最大值，
    pos为当前已经确定的left数组的右边界，
    curMax为当前已经发现的最大值。
    对于下一个数A[i]：
        如果A[i] < leftMax，则它必须被包含在left中（否则right中将出现比left的最大值更小的数）。
        因此需要更新pos = i，leftMax = curMax（因为left是连续的，所以之前已经发现过的最大值现在也必须包含于left中）。

        如果leftMax <= A[i] <= curMax，则这个数有可能被包含在left中（如果右侧出现比leftMax更小的数），
        也有可能不需要（如果右侧没有出现这样的数）。所以暂时不作处理。

        如果A[i] > curMax，则这个数也不一定被包含在left中,，因为后边可能还有更大的（和前一种情况同理）。更新curMax = A[i],
        既然是要left最小，那么先不更新pos 。如果这一题改一下为left取最大，则在这个地方更新pos，而在情况1的时候不更新。

        最后的结果为pos+1
     */

    /*
    以数组[5, 7, 3, 8, 6, 9, 10]为例。初始时，令leftMax = 5，curMax = 5，pos = 0。

Initial State:
leftMax = 5, curMax = 5, pos = 0
5  7  3  8  6  9  10
l  -  -  -  -  -  -

i = 1: Update curMax to 7
leftMax = 5, curMax = 7, pos = 0
5  7  3  8  6  9  10
l  ^  -  -  -  -  -

i = 2: Update leftMax to 7 and j to 2
leftMax = 7, curMax = 7, pos = 2
5  7  3  8  6  9  10
l  l  l  -  -  -  -

i = 3: Update curMax to 8
leftMax = 7, curMax = 8, pos = 2
5  7  3  8  6  9  10
l  l  l  ^  -  -  -

i = 4: Update leftMax to 8, j to 4
leftMax = 8, curMax = 8, pos = 4
5  7  3  8  6  9  10
l  l  l  l  l  -  -

i = 5: Update curMax to 9
leftMax = 8, curMax = 9, pos = 4
5  7  3  8  6  9  10
l  l  l  l  l  ^  -

i = 6: Update curMax to 10
leftMax = 8, curMax = 10, pos = 4
5  7  3  8  6  9  10
l  l  l  l  l  ^  ?
     */
    public int partitionDisjoint3(int[] A) {

        int leftMax = A[0], curMax = A[0], pos = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < leftMax) {
                pos = i;
                leftMax = curMax;
            }
            else if (A[i] > curMax) {
                curMax = A[i];
            }

            else {

            }
        }
        return pos + 1;
    }

    /*
    Method4: 辅助数组
     */
    public int partitionDisjoint4(int[] A) {
        if (A == null || A.length <= 1) return -1;
        int len = A.length;
        int[] minRight = new int[len];
        minRight[len - 1] = A[len - 1];

        for (int i = len - 2; i >= 0; i--) {
            minRight[i] = Math.min(A[i], minRight[i + 1]);
        }

        int maxLeft = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            maxLeft = Math.max(maxLeft, A[i]);
            if (maxLeft <= minRight[i + 1]) return i + 1;
        }

        //Never run this
        return 0;
    }


    @Test
    public void test() {
        System.out.println(partitionDisjoint2(
                new int[]{5, 0, 3, 8, 6})
        );
    }
}
