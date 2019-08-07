package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

public class FindMedianSortedArrays4 {
    /*
    二分法
    要求The overall run time complexity should be O(log (m+n)).
    If we see log(n), we should think about using binary something.
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int n = A.length + B.length;

        if (n % 2 == 0) {
            return (findKth(A, B, n / 2) + findKth(A, B, n / 2 + 1)) / 2.0;
        }

        return findKth(A, B, n / 2 + 1);
    }

    // 查找A，B两个有序数组合并后的第k个数， k is not zero-based, it starts from 1
    public int findKth(int[] A, int[] B, int k) {
        if (A.length == 0) {
            return B[k - 1];
        }
        if (B.length == 0) {
            return A[k - 1];
        }

        //这是对数值进行二分
        int start = Math.min(A[0], B[0]);
        int end = Math.max(A[A.length - 1], B[B.length - 1]);

        // find first x that >= k numbers is smaller or equal to x
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (countSmallerOrEqual(A, mid) + countSmallerOrEqual(B, mid) < k) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (countSmallerOrEqual(A, start) + countSmallerOrEqual(B, start) >= k) {
            return start;
        }

        return end;
    }

    //一个有序数组arr中小于等于number的数字个数
    //TODO 二分法的一个变种
    private int countSmallerOrEqual(int[] arr, int number) {
        //这是对idx进行二分
        int start = 0, end = arr.length - 1;

        // find first index that arr[index] > number;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= number) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (arr[start] > number) {
            return start;
        }

        if (arr[end] > number) {
            return end;
        }

        return arr.length;
    }

    @Test
    public void test(){
        findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4, 5});
        System.out.println(countSmallerOrEqual(new int[]{2,3,5},1));
    }

}
