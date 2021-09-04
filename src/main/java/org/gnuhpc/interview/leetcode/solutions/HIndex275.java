package org.gnuhpc.interview.leetcode.solutions;

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

    /*
    func hIndex(citations []int) int {
	if len(citations) == 0 {
		return 0
	}

	left, right := 0, len(citations)-1
	var mid int
	var h int

	for left <= right {
		mid = left + (right-left)>>1
		if citations[mid] >= len(citations)-mid  {
			h = len(citations)-mid
			right = mid-1
		} else {
			left = mid+1
		}
	}
	return h
}

     */

}
