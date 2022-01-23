package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2021/12/26
 */
public class PourWater755 {
    public static int[] pourWater(int[] heights, int volume, int k) {
        //外部循环volume次，代表下滴volume次水
        for (int j = 0; j < volume; j++) {
            int h = heights[k];
            int minIndex = k;
            int min = h;
            //尝试找到左侧局部最小值
            for (int i = k - 1; i >= 0; i--) {
                //若当前高度大于最小高度，说明水滴不可能传播到更向左的index处，停止寻找
                if (heights[i] > min) break;
                //更新最小值，注意不是<=,因为题目要求相同高度时停留在最靠近k的index处
                if (heights[i] < min) {
                    minIndex = i;
                    min = heights[i];
                }
            }

            //如果在左侧找到，则更新高度，并处理下一滴水
            if (minIndex != k) {
                heights[minIndex]++;
                continue;
            }

            //右侧寻找逻辑同上
            for (int i = k + 1; i < heights.length; i++) {
                if (heights[i] > min) break;
                if (heights[i] < min) {
                    minIndex = i;
                    min = heights[i];
                }
            }

            heights[minIndex]++;
        }

        return heights;
    }
}
