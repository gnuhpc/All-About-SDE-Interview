package org.gnuhpc.interview.leetcode.solutions;

/*
解题思路
首先，一个&的计算里，只要有一个数是0,那么答案就为0；
然后看一个规律,如下所示！你计算的结果其实是m 和 n 的 共同前缀 1110 的与，然后向左移四位！

224 1110 0000  -> m
225 1110 0001
226 1110 0010
227 1110 0011
228 1110 0100
229 1110 0101
230 1110 0110
231 1110 0111
232 1110 1000
233 1110 1001
234 1110 1010
235 1110 1011
236 1110 1100
237 1110 1101
238 1110 1110
239 1110 1111 -> n
result:1110 0000

作者：yi-dai-mi-kang-ji-lou
链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/solution/jie-fa-jian-dan-xiang-xi-zhu-shi-by-yi-dai-mi-kang/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

 */
public class RangeBitwiseAnd201 {
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        while (n > m) { //n == m 的时候退出
            m >>>= 1;
            n >>>= 1;
            count++;
        }
        return m << count;
    }

}
