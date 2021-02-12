package org.gnuhpc.interview.leetcode.solutions;

public class NumberOfSubstrings1358 {
    /**
     * 双指针。
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public int numberOfSubstrings(String s) {
        int cnt = 0;
        int n = s.length();
        int[] arr = new int[3];// 标记窗口内a、b、c的数量
        for (int l = 0, r = 0; r < n; r++) {
            arr[s.charAt(r) - 'a']++;
            while (arr[0] > 0 && arr[1] > 0 && arr[2] > 0) {// 当窗口内的a、b、c都至少出现一次
                cnt += n - r;// 注意这里加上的是 n-r， 因为此时再加入窗口右侧的字符也满足a、b、c都至少出现一次
                arr[s.charAt(l++) - 'a']--;// 左边界移动，子串的唯一性以左边界为基准
            }
        }
        return cnt;
    }
}
