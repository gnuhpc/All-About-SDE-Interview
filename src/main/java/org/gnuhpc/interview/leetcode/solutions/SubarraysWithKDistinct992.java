package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/*
ExactlyK = atMostK - atMostK-1

【 atMostK 滑动窗口模板】

哈希表或者数组维护窗口中<元素，出现次数>

for (枚举右边界)) {
    右边界频次
    while (元素种类 > K)) {
        左边界频次
        去除频次为0的元素（哈希表的情况）
        缩左边界
    }
    更新结果
}
返回结果

 */
public class SubarraysWithKDistinct992 {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMost(A, K) - atMost(A, K - 1);
    }

    private int atMost(int[] A, int K) {
        if (K == 0) return 0;
        int[] numCount = new int[A.length + 1];//相对于map，记录在窗口内每个值的数量
        int l = 0, r = 0, count = 0, size = 0;//size作用：记录有窗口内有多少个不同数字
        for (int i = 0; i < A.length; i++) {
            numCount[A[r]]++;
            if (numCount[A[r]] == 1) size++;    //新加入的是新的
            while (size > K) {
                numCount[A[l]]--;
                if (numCount[A[l]] == 0) size--;    //数量为0，去掉这个之后不同数量少了一个
                l++;
            }
            count += r - l + 1; //添加每一个i结尾的符合要求子数组数量
            r++;
        }
        return count;
    }


    @Test
    public void test() {
        System.out.println(subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3));
    }
}
