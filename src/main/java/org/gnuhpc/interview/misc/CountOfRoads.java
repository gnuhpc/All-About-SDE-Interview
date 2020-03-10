package org.gnuhpc.interview.misc;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.*;


// Rakuten 面试题
//https://app.codility.com/programmers/task/min_router_peripherality/
public class CountOfRoads {
    public int[] solution(int[] T) {
        if (T == null) return null;
        int[] res = new int[T.length - 1];
        int[] depth = new int[T.length];
        Map<Integer, List<Integer>> adjs = new HashMap<>();
        int capital = -1;


        for (int i = 0; i < T.length; i++) {
            if (i == T[i]) {
                capital = i;
            } else {

                List<Integer> l = adjs.get(i);
                if (l == null) l = new ArrayList<>();
                l.add(T[i]);

                adjs.put(i, l);

                List<Integer> l2 = adjs.get(T[i]);
                if (l2 == null) l2 = new ArrayList<>();
                l2.add(i);

                adjs.put(T[i], l2);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(capital);
        depth[capital] = 0;
        boolean[] visited = new boolean[T.length];
        visited[capital] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int adj : adjs.get(cur)) {
                if (visited[adj]) continue;
                depth[adj] = depth[cur] + 1;
                res[depth[adj] - 1]++;
                visited[adj] = true;
                q.add(adj);
            }
        }

        return res;
    }

    @Test
    public void test() {
        Utils.printArray(solution(new int[]{9, 1, 4, 9, 0, 4, 8, 9, 0, 1}));
    }
}
