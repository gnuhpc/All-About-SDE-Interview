package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;

public class HIndex274 {
    // add by Tina,
    // 方法一，先排序，后遍历找到citations[i] >= n-i即可
    // T=O(nlogn)|M=O(1)
    public int hIndex(int[] citations) {
        if(citations == null ) return 0;
        Arrays.sort(citations);
        int n = citations.length;
        for(int i = 0;i < n; i++){
            if(citations[i] >= n-i) return n-i;
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
        for(int c : citations)
            if(c >= n) count[n]++;  //当引用数大于等于 n 时，我们均将其数量计入 count[n]中
            else count[c]++;
        for(int i = n; i > 0; i--) {  //从后面开始遍历
            if(count[i] >= i) return i;
            count[i-1] += count[i];  //引用数大于 i-1 的数量是i-1及之后的累加
        }
        return 0;
    }



}
