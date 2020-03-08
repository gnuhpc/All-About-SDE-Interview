package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.Arrays;

public class HIndex274 {
    /*
    The definition of h-index in the problem is confusing. Actually, according to Wikipedia,


    某人的h指数是指他至多有h篇论文分别被引用了至少h次。
    其计算方法非常简单，例如，
    某人的h指数是25，这表示他已发表的论文中，每篇被引用了至少25次的论文总共有25篇。

    => 我们要在数组中找一个最大的 h，使得后 h 个数大于等于 h，
/*
* 思路：
*
* 1、首先看到大于等于某个值自然而然的想到将数组排序；
*
* 2、将数组排序之后，对于给定的某个i，我们知道有citations.length - i篇论文的引用数 ≥ citations[i]，i篇
*    论文的引用数 < citations[i]；
*
* 3、不妨设h = citations.length - i，即至多有h篇论文分别引用了至少citation[i]次，其余citations.length - h篇
*    论文的引用数不多于citation[i]次。
*
*    既然如此，只要citation[i] ≥ h，就满足题意。
*/

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;
            if (h <= citations[i]) {
                return h;
            }
        }
        return 0;

    }

    // 方法二，借助hash表，hash表最重要的是明确其下标和值的含义
    // 本题中定义一个长度为n的数组，表示hash表
    // 数组下标为文章引用数量，数组值该下标引用数量的文章数量
    // 注意1关于引用数量>=n的情况的处理；
    // 注意2从后往前遍历，当当前数字不满足条件时，将当前计数累加到i-1上
    // T=O(n)|M=O(n)
    public int hIndex2(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];
        for (int c : citations)
            if (c >= n) count[n]++;  //当引用数大于等于 n 时，我们均将其数量计入 count[n]中
            else count[c]++;
        for (int i = n; i > 0; i--) {  //从后面开始遍历
            if (count[i] >= i) return i;
            count[i - 1] += count[i];  //引用数大于 i-1 的数量是i-1及之后的累加
        }
        return 0;
    }


}
