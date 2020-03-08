package org.gnuhpc.bigdata.leetcode.solutions;

public class FindKthNumber668 {
    public int findKthNumber(int m, int n, int k) {
        int lo = 1;
        int hi = m * n;
        while (lo <= hi) {//二分查找
            int mid = lo + (hi - lo) / 2;
            //找出比mid还小的数字个数
            int count = countLess(m, n, mid);
            if (count < k) {
                lo = mid + 1; //到右半边找
            }
            else {
                hi = mid - 1;// 到左半边找
            }
        }
        return lo;
    }

    public static int countLess(int m, int n, int target) {
        int row = m;
        int col = 1;
        int count = 0;
        while (row >= 1 && col <= n) {
            if (row * col > target) {
                row--;
            }
            else {
                count += row;
                col++;
            }
        }
        return count;
    }

}
