package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class IntegerReplacement397 {
    /*
    Method1 : DFS + Memo
    1. With the helper of  hashmap,
    we don't need to search for one intermediate result multiple times
    2. To hand the overflow for test case INT.MAX,
    use ``` 1 + (n - 1) / 2 ``` instead of ``` (n + 1) / 2```.
    The idea comes from solving some binary search questions.
    To avoid overflow, we use ``` int mid = start + (end - start) / 2 ```
     instead of ``` int mid = (start + end) / 2```
     */
    public int integerReplacement(int n) {
        Map<Integer, Integer> map = new HashMap<>();

        map.put(1, 0);
        map.put(2, 1);

        return helper(n, map);
    }

    private int helper(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }

        int steps = -1;
        if (n % 2 == 0) {
            steps = helper(n / 2, map) + 1;
        } else {
            //另外注意处理防止溢出以外，这个help的递归方向也很重要，
            // 要一直越来越收敛，所以n+1和除以2合并后作为两步进行递归搜索
            steps = Math.min(helper((n - 1), map) + 1, helper(1 + (n - 1) / 2, map) + 2);
        }

        map.put(n, steps);

        return steps;
    }

    /*
    Method: BFS. 碰上深度最小的都可以用BFS来一层做掉
     */
    public int integerReplacement2(int n) {
        assert n > 0;
        Queue<Long> queue = new LinkedList<>();
        queue.offer((long) n);
        return bfs(queue, 0);
    }

    private int bfs(Queue<Long> q, int level) {
        int size = q.size();
        int i = 0;
        while (!q.isEmpty() && i < size) {
            long n = q.poll();
            if (n == 1) {
                return level;
            }
            if (n % 2 == 0) {
                q.offer(n / 2);
            } else {
                q.offer(n + 1);
                q.offer(n - 1);
            }
            i++;
        }
        return bfs(q, level + 1);
    }

    @Test
    public void test() {
        System.out.println(integerReplacement2(7));
    }
}
