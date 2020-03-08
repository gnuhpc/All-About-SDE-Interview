package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import static org.gnuhpc.bigdata.leetcode.utils.Utils.swap;

public class SortArrayByParityII922 {
    public int[] sortArrayByParityII(int[] A) {

        int i = 0, j = 1;

        while (i < A.length && j < A.length) {
            int numi = A[i], numj = A[j];

            boolean f1 = isEven(numi), f2 = isEven(numj);

            if (f1 && !f2) {
                i += 2;
                j += 2;
            }
            else if (!f1 && f2) {
                swap(A, i, j);
                i += 2;
                j += 2;
            }
            else if (f1 && f2) {
                i += 2;
            }
            else {
                j += 2;
            }
        }

        return A;
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    @Test
    public void test() {
        Utils.printArray(sortArrayByParityII(new int[]{4, 2, 5, 7}));
    }
}
