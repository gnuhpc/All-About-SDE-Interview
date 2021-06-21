package org.gnuhpc.interview.leetcode.solutions;

public class PeakIndexInMountainArray852 {
    public int peakIndexInMountainArray(int[] arr) {
        return helper(arr, 0, arr.length - 1);
    }

    public int helper(int[] num, int start, int end) {
        if (start == end) {
            return start;
        } else if (start + 1 == end) {
            if (num[start] > num[end]) return start;
            return end;
        } else {
            int m = (start + end) / 2;
            if (num[m] > num[m - 1] && num[m] > num[m + 1]) {
                return m;
            } else if (num[m - 1] > num[m] && num[m] > num[m + 1]) {
                return helper(num, start, m - 1);
            } else {
                return helper(num, m + 1, end);
            }
        }
    }
}

/*
func peakIndexInMountainArray(A []int) int {
    for {
		if len(A) == 1 {
			return 0
		}
		if len(A) == 2 {
			if A[0] > A[1] {
				return 0
			}
			return 1
		}
		mid := len(A) / 2

		if A[mid] >= A[mid-1] && A[mid] > A[mid+1] {
			return mid
		}

		if A[mid] < A[mid-1] {
			return peakIndexInMountainArray(A[:mid])
		}

		if A[mid] < A[mid+1] {
			return mid + peakIndexInMountainArray(A[mid:])
		}
	}
}
 */
