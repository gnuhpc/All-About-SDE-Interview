package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2020/5/8
 */
public class NextGreaterElement496 {
    /*
    我们可以忽略数组 nums1，先对将 nums2 中的每一个元素，求出其下一个更大的元素。
    随后对于将这些答案放入哈希映射（HashMap）中，再遍历数组 nums1，并直接找出答案。对于 nums2，我们可以使用单调栈来解决这个问题。
作者：LeetCode
链接：https://leetcode-cn.com/problems/next-greater-element-i/solution/xia-yi-ge-geng-da-yuan-su-i-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> s = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[nums1.length];

        int i = 0;
        while (i < nums2.length) {
            if (s.isEmpty() || s.peek() > nums2[i]) {
                s.push(nums2[i++]);
            } else {
                map.put(s.pop(), nums2[i]);
            }
        }

        //没有剩下的都是右边没有更大元素了
        while (!s.isEmpty()) {
            map.put(s.peek(), -1);
            s.pop();
        }
        for (int j = 0; j < nums1.length; j++) {
            res[j] = map.get(nums1[j]);
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(nextGreaterElement(new int[]{1, 7, 5, 3}, new int[]{2, 3, 5, 1, 0, 7, 3}));
    }
}
