package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/9/11
 */
public class FindIntegers600 {
    int res = 1;

    public int c(int n) {
        dfs(1, n);
        return res;
    }

    public void dfs(int cur, int n) {
        if (cur > n) return;
        res++;
        if ((cur & 1) == 1) {
            // 补零
            dfs(cur << 1, n);
        } else {
            // 补0 或者 1
            dfs(cur << 1, n);
            dfs((cur << 1) + 1, n);
        }
        return;
    }


    /*
    我们可以从1开始直接统计二进制不会有连续的1的元素，要想让其二进制不会含有连续的1，我们可以判断每一个元素其二进制的最低位是否是1，若最低位是1则向左移1位，若最低位是0，则我们可以想左移动1位或者向左移动1位后在加1，这样可以避免有二进制出现连续的1.

作者：_YES
链接：https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/solution/bu-hui-lian-xu-1de-fei-fu-zheng-shu-by-_-fu7l/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
