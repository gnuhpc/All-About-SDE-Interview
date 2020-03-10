package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
  Method1: 反向贪心，因为后边的步骤会覆盖前边的结果，用反向会消除前边步骤的影响
 */
public class MovesToStamp936 {
    public int[] movesToStamp(String stamp, String target) {
        char[] S = stamp.toCharArray();
        char[] T = target.toCharArray();
        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[T.length];
        int stars = 0;

        while (stars < T.length) {
            boolean doneReplace = false;
            for (int i = 0; i <= T.length - S.length; i++) {
                if (!visited[i] && canReplace(T, i, S)) {
                    stars += doReplace(T, i, S.length);
                    doneReplace = true;
                    visited[i] = true;
                    res.add(i);
                    if (stars == T.length) {
                        break;
                    }
                }
            }

            if (!doneReplace) {
                return new int[0];
            }
        }


        Collections.reverse(res);

        return res.stream().mapToInt(i -> i).toArray();
    }

    // p为启示匹配位置
    private boolean canReplace(char[] T, int p, char[] S) {
        for (int i = 0; i < S.length; i++) {
            if (T[i + p] != '*' && T[i + p] != S[i]) {
                return false;
            }
        }
        return true;
    }

    private int doReplace(char[] T, int p, int len) {
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (T[i + p] != '*') {
                T[i + p] = '*';
                count++;
            }
        }
        return count;
    }


    @Test
    public void test() {
        System.out.println(movesToStamp("abc", "ababc"));
    }
}
