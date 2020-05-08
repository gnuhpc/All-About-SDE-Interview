package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;


public class LargestRectangleArea84 {
    /*
    Method1: 从中间散开去，找比它大于等于的，计算面积
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        // 特判
        if (len == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < len; i++) {

            // 找左边最后 1 个大于等于 heights[i] 的下标
            int left = i;
            int curHeight = heights[i];
            while (left > 0 && heights[left - 1] >= curHeight) {
                left--;
            }

            // 找右边最后 1 个大于等于 heights[i] 的索引
            int right = i;
            while (right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }

            int width = right - left + 1;
            res = Math.max(res, width * curHeight);
        }
        return res;
    }

    /*
        Method2: 单调栈, 注意放入的是idx，因为要计算宽度
        我们在遍历的时候，需要记录的是下标，如果当前的高度比它之前的高度严格小于的时候，就可以直接确定之前的那个高的柱形的最大矩形的面积，为了确定这个最大矩形的左边界，我们还要找到第一个严格小于它的高度的矩形，向左回退的时候，其实就可以当中间这些柱形不存在一样。

作者：liweiwei1419
链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int largestRectangleArea2(int[] heights) {
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Deque<Integer> s = new LinkedList<>();

        int max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        int area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < heights.length) {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.isEmpty() || heights[s.peek()] < heights[i])//可能会更大，因此往后走
                s.push(i++);

                // If this bar is lower than top of stack, then calculate area of rectangle
                // with stack top as the smallest (or minimum height) bar. 'i' is
                // 'right index' for the top and element before top in stack is 'left index'
            else {
                tp = s.pop();  // store the top index

                // Calculate the area with hist[tp] stack as smallest bar
                area_with_top = heights[tp] * (s.isEmpty() ? i : i - s.peek() - 1);

                // update max area
                max_area = Math.max(max_area, area_with_top);
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (!s.isEmpty()) {
            tp = s.pop();
            area_with_top = heights[tp] * (s.isEmpty() ? i : i - s.peek() - 1);
            max_area = Math.max(max_area, area_with_top);
        }

        return max_area;
    }

    @Test
    public void test() {
        System.out.println(largestRectangleArea(new int[]{2, 3, 5, 4}));
    }
}
