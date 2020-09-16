package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import javax.rmi.CORBA.Util;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FindDiagonalOrder498 {
    class Pair {
        public int x;
        public int y;
        public int level;

        Pair(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }

        @Override
        public boolean equals(Object obj)
        {

            if(this == obj)
                return true;

            if(obj == null || obj.getClass()!= this.getClass())
                return false;

            Pair pair = (Pair) obj;


            return (pair.x == this.x && pair.y == this.y);
        }

        @Override
        public int hashCode() {

            return this.x * 31 + this.y;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    int n;
    int m;
    public int[] findDiagonalOrder(int[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;

        int[] res = new int[n*m];

        LinkedList<Pair> q = new LinkedList<>();
        Pair init = new Pair(0, 0, 0);
        q.offer(init);
        Set<Pair> visited = new HashSet<>();
        visited.add(init);

        int i = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            Pair p = null;
            List<Integer> tmp = new LinkedList<>();
            for (int j = 0; j < size; j++) {
                p = q.poll();
                tmp.add(matrix[p.x][p.y]);
                Pair newP1 = new Pair(p.x + 1, p.y, p.level + 1);
                if (isValid(newP1) && !visited.contains(newP1)) {
                    q.offer(newP1);
                    visited.add(newP1);
                }

                Pair newP2 = new Pair(p.x, p.y + 1, p.level + 1);
                if (isValid(newP2) && !visited.contains(newP2)) {
                    q.offer(newP2);
                    visited.add(newP2);
                }
            }

            if (p.level % 2 == 0) {
                for (int n : tmp) res[i++] = n;
            } else {
                for (int k = tmp.size() - 1; k >= 0; k--) res[i++] = tmp.get(k);
            }
        }
        return res;
    }

    boolean isValid(Pair pair) {
        return (pair.x >= 0 && pair.x < n) && (pair.y >= 0 && pair.y < m);
    }

    @Test
    public void test() {
        Utils.printArray(findDiagonalOrder(new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        }));
    }
}
