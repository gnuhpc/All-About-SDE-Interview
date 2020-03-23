package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

/**
 * Copyright gnuhpc 2020/3/22
 */

public class MinIncrementForUnique945 {
    public int minIncrementForUnique(int[] A) {
        int res = 0;
        int[] bucket = new int[80000];//想象有这么多桶，A中的元素n是标号为n的球，为什么是40000*2，因为最坏情况40000个元素都是40000

        //把球放在桶里
        for (int n : A) {
            bucket[n]++;
        }

        for (int i = 0; i < bucket.length - 1; ) {
            if (bucket[i] > 1) {
                res += bucket[i] - 1;//只剩下一个，其余的统统移动
                bucket[i + 1] += (bucket[i] - 1);//移动到下一个桶中去
                bucket[i] = 1;//当前的只剩下一个
            } else {
                i++;//满足条件往下走
            }
        }

        //最后一个桶如果最后大于1，说明球都堆到了最后一个桶里，移动只剩下一个就行了。
        if (bucket[bucket.length - 1] > 1) res += (bucket[bucket.length - 1] - 1);

        return res;
    }

    @Test
    public void test() {
        System.out.println(minIncrementForUnique(new int[]{3, 2, 1, 2, 1, 7}));
    }
}
