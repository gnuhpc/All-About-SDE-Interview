package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

//Reference ：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
public class FindMedianSortedArrays4 {
    /*
    Method1 : 暴力
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        if (m == 0) {
            if (n % 2 == 0) {
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            }
            else {
                return nums2[n / 2];
            }
        }
        if (n == 0) {
            if (m % 2 == 0) {
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            }
            else {
                return nums1[m / 2];
            }
        }

        int count = 0;
        int i = 0, j = 0;
        while (count != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            }
            else {
                nums[count++] = nums2[j++];
            }
        }

        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        }
        else {
            return nums[count / 2];
        }
    }

    /*
    Method2: 双指针 比方法1优化了空间复杂度， 有点类似DP滚动数组优化
     */
    public double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            }
            else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }

    /*
    Method3: 双二分法
    要求The overall run time complexity should be O(log (m+n)).
    If we see log(n), we should think about using binary something.
     */
    public double findMedianSortedArrays3(int[] A, int[] B) {
        int n = A.length + B.length;

        if (n % 2 == 0) {
            return (findKth(A, B, n / 2) + findKth(A, B, n / 2 + 1)) / 2.0;
        }

        return findKth(A, B, n / 2 + 1);
    }

    // 查找A，B两个有序数组合并后的第k个数， k is not zero-based, it starts from 1
    public int findKth(int[] A, int[] B, int k) {
        if (A.length == 0) { //判断空
            return B[k - 1];
        }
        if (B.length == 0) { //判断空
            return A[k - 1];
        }

        if (A[A.length - 1] < B[0]) { //判断根本没有交集 ， B整体大于A
            if (A.length >= k) return A[k - 1];
            else return B[k - A.length - 1];
        }

        if (B[B.length - 1] < A[0]) { // 判断根本没有交集， A整体大于B
            if (B.length >= k) return B[k - 1];
            else return A[k - B.length - 1];
        }

        //这是对数值进行二分
        int start = Math.min(A[0], B[0]);
        int end = Math.max(A[A.length - 1], B[B.length - 1]);

        // find first x that >= k numbers is smaller or equal to x
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (countSmallerOrEqual(A, mid) + countSmallerOrEqual(B, mid) < k) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (countSmallerOrEqual(A, start) + countSmallerOrEqual(B, start) >= k) {
            return start;
        }

        return end;
    }

    //一个有序数组arr中小于等于number的数字个数
    //二分法的一个变种
    private int countSmallerOrEqual(int[] arr, int number) {
        //这是对idx进行二分
        int start = 0, end = arr.length - 1;

        // find first index that arr[index] > number;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= number) {
                start = mid;
            }
            else {
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

    // add by tina
    // 注意k从1开始
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];
        if (k == 1) return Math.min(nums1[i], nums2[j]);
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        }
        else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }

    @Test
    public void test() {
        findMedianSortedArrays4(new int[]{1, 3}, new int[]{2, 4, 5});
        System.out.println(countSmallerOrEqual(new int[]{2, 3, 5}, 1));
    }

}
