package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/5/13
 */
public class MaxChunksToSorted769 {
    //求最多几块也就是说要割开的越细越好，但是也不能太细，否则某个数字到达不了自己该去的地方（即数字i应该放在下标i的地方）
    //一个块可以跨越的边界到当前该数字到达的下标即可，再扩展就不是最多几块了
    public int maxChunksToSorted(int[] arr) {
        int res = 0, n = arr.length;
        for (int i = 0; i < n; ) {
            int shouldMax = arr[i], j = i + 1;//shouldMax为能在一个块的最大值
            boolean isReach = true;
            for (; j <= shouldMax; ++j) {
                if (arr[j] > shouldMax) {//中间有人没办法让他到达他该去的地方了
                    isReach = false;
                    break;
                }
            }
            if (isReach) { //到达了
                res++;//赶紧分块
                i = shouldMax + 1;//接着往下走
            } else {
                i = j;//没达到的话，跳过i--(j-1)之间的块，因为肯定都在一个块中，从j开始往后再找
            }
        }
        return res;
    }

    public int maxChunksToSorted2(int[] arr) {
        //当遍历到第i个位置时，如果可以切分为块，那前i个位置的最大值一定等于i。
        //否则，一定有比i小的数划分到后面的块，那块排序后，一定不满足升序。
        int res = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);//统计前i个位置的最大元素
            if (max == i) res++;
        }
        return res;
    }
}
