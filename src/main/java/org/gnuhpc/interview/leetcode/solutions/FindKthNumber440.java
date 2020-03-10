package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2019/9/22
 */
//https://leetcode.flowerplayer.com/2019/04/10/leetcode-440-k-th-smallest-in-lexicographical-order%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/
public class FindKthNumber440 {
    public int findKthNumber(int n, int k) {
        long current = 1; // 从字典第一位1开始查找
        k--; // 除去第一位
        // 当k变为0时，当前节点即是结果
        while (k > 0) {
            // 取得当前节点的所有子节点数量
            int childrenCount = getChildrenCount(current, n);
            // 如果k小于等于当前节点的所有子节点数，说明第k个数在当前节点的树目录中
            if (k <= childrenCount) {
                // 将当前节点纵向向下移动一层，消费掉k的一位
                k--;
                // 向下纵向移动，当前数变为原来十倍
                current *= 10;
            }
            // 如果k大于当前节点的所有子节点数，说明第k位在当前节点的所有子节点之后
            else if (k > childrenCount) {
                // 移动到树的右边一个节点，消费掉2个节点间的子节点数量
                k -= (childrenCount + 1);
                // 向右移动，当前数变为原来加一
                current++;
            }
        }
        return (int) current;
    }

    // 取得当前节点的所有子节点数
    private int getChildrenCount(long current, int n) {
        long childrenCount = 0; // 所有子节点数
        long maxChild = current; // 当前层理论最大子节点的值
        long maxChildrenCount = 1; // 当前层理论最多节点数量
        while (current * 10 <= n) {
            // 每向下一层，当前层的子节点数是上一层的10倍，再加上之前层的子节点为总数
            childrenCount = childrenCount + maxChildrenCount * 10;
            // 当前层理论最多节点数量乘以10，为了下一层计算使用
            maxChildrenCount *= 10;
            // 当前层理论最大子节点的值为为上一层最大值乘以10再加上9
            maxChild = maxChild * 10 + 9;
            // 如果理论最大值大于n，计算出实际子节点数量
            if (maxChild > n) {
                childrenCount = childrenCount - (maxChild - n);
                break;
            }
            // 向下移动一层，当前数变为原来10倍
            current *= 10;
        }
        return (int) childrenCount;
    }
}
