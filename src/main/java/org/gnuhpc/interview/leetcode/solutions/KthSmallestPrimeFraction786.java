package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright gnuhpc 2020/9/6
 */
public class KthSmallestPrimeFraction786 {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        int len = A.length;
        int nodeCount = len * (len - 1) / 2;
        if (K > nodeCount) {
            return new int[0];
        }
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o2.val.compareTo(o1.val));
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                Node node = new Node(A[i], A[j]);
                if (queue.size() < K) {
                    queue.offer(node);
                } else if (queue.peek().val > node.val) {
                    queue.poll();
                    queue.add(node);
                }
            }
        }

        Node rs = queue.peek();
        return new int[]{rs.fenzi, rs.fenmu};
    }

    class Node {
        Double val;
        int fenzi;
        int fenmu;

        Node(int fenzi, int fenmu) {
            this.fenzi = fenzi;
            this.fenmu = fenmu;
            val = (double) fenzi / (double) fenmu;
        }
    }

    /*
    如果想查看本题目是哪家公司的面试题，请参考以下免费链接： https://leetcode.jp/problemdetail.php?id=786

解题思路分析：

题目给出的数组已经按照升序方式排列好，因此我们可以快速的得知，数组中第一个数与最后一个数组成的分数一定是最小的，而第二小的可能是第一个数与倒数第二个数组成的分数N1，或者是第二个数与最后一个数组成的分数N2，到这一步我们就无法再继续判断下去了。因此，解题时，我们可以建立一个PriorityQueue，按照大小顺序来存储所有分数，然后取出第K个元素就是第K小的分数。

不过这样做需要将所有分数都列举出来，大概率会超时。所以我们可以考虑先将所有数字与第一个数字组成的分数存入Queue。接下来从Queue中取出第一个元素，它一定最小的那个（首元素与尾元素组成的分数），然后我们将这个元素的分子向后移动一位，即利用第二个元素与尾元素组成一个新的分数，这个分数是稍微大于最小分数的一个数字，也是上文提到过的N2，我们将它插入到Queue中，这时Queue中一定存在N1和N2两个相对最小的分数，我们再从Queue中取出最小的一个，重复上述过程，直到第K次取出的元素即是本题的解。
     */
    public int[] kthSmallestPrimeFraction2(int[] A, int K) {
        // 排序用的Queue，
        // Queue中存储的是分子index，分母index，以及分数的值
        PriorityQueue<double[]> q
                = new PriorityQueue<>((a, b) -> a[2] - b[2] > 0 ? 1 : -1);
        // 将所有数数字与首元素组成的分数存入Queue
        for (int i = 1; i < A.length; i++) {
            q.offer(new double[]{0, i, (double) A[0] / A[i]});
        }
        // 返回结果
        int[] res = new int[2];
        // 循环K次，每次从Queue中取出最小元素
        for (int i = 1; i <= K; i++) {
            // 取出最小元素
            double[] arr = q.poll();
            // 如果当前是第K次，
            if (i == K) {
                // 将当前元素的分子与分母下标赋值到返回结果
                res[0] = A[(int) arr[0]];
                res[1] = A[(int) arr[1]];
                break;
            }
            // 将分子对应数组中的下标变大一位
            arr[0]++;
            // 重新计算当前分数的值
            arr[2] = (double) A[(int) arr[0]] / A[(int) arr[1]];
            // 将当前分数加入Queue
            q.offer(arr);
        }
        return res;
    }
}
