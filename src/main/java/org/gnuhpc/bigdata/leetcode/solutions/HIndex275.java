package org.gnuhpc.bigdata.leetcode.solutions;

public class HIndex275 {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int n = citations.length;
        return hIndexHelp(citations, n, 0, n - 1);
    }

    public int hIndexHelp(int[] citations, int n, int left, int right) {
        // 递归退出条件，注意需要判断right节点是否满足条件，如果不满足则应=0
        if (left >= right) {
            if (citations[right] >= n - right) return n - right;
            else return 0;
        }
        int mid = left + (right - left) / 2;

        if (citations[mid] >= n - mid)
            return hIndexHelp(citations, n, left, mid);
        else
            return hIndexHelp(citations, n, mid + 1, right);

    }

    //非递归二分：我们的任务是： 在数组中找一个最大的 h，使得后 h 个数大于等于 h，进行二分查找即可。
    public int hIndex2(int[] citations) {
        int len = citations.length;

        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            if (citations[0] == 0) {
                return 0;
            }
            else {
                return 1;
            }
        }

        int i = 0;
        int j = len - 1;
        while (i < j) {
            int m = i + (j - i + 1) / 2;
            if (citations[m] > len - m) {
                j = m - 1;
            }
            else {
                i = m;
            }
        }

        if (citations[j] > len - j) {
            return len;
        }

        if (citations[j] == len - j) {
            return len - j;
        }
        else {
            return len - j - 1;
        }
    }
}
