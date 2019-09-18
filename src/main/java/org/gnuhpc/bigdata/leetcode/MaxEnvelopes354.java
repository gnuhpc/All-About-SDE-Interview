package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/*
在宽度相等的时候，让高度不能出现“上升的子序列”。即首先按照宽度“升序排序”，在宽度相等的时候，按照高度“降序排序”，这样就能保证宽度相等的信封，最多只能选一个，这种策略保证了结果的正确性。

作者：liweiwei1419
链接：https://leetcode-cn.com/problems/russian-doll-envelopes/solution/tan-xin-suan-fa-er-fen-cha-zhao-python-dai-ma-java/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MaxEnvelopes354 {
    public int maxEnvelopes(int[][] envelopes) {

        int len = envelopes.length;
        if (len < 2) {
            return len;
        }

        Arrays.sort(envelopes, (envelope1, envelope2) -> {
            if (envelope1[0] != envelope2[0]) {
                return envelope1[0] - envelope2[0];
            }
            return envelope2[1] - envelope1[1];
        });


        //tail 是高度数组
        int[] tail = new int[len];
        tail[0] = envelopes[0][1];

        // end 表示有序数组 tail 的最后一个已经赋值元素的索引
        int end = 0;

        for (int i = 1; i < len; i++) {
            int target = envelopes[i][1];

            if (target > tail[end]) {
                end++;
                tail[end] = target;
            } else {
                int left = 0;
                int right = end;

                //LIS
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (tail[mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                tail[left] = target;
            }
        }
        return end + 1;
    }
}
