package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class PathSum666 {
    private int total = 0;

    public int pathSum(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i] / 10;
            int value = nums[i] % 10;
            map.put(key, value);
        }
        helper(11, 0, map);
        return total;
    }

    private void helper(int root, int sum, Map<Integer, Integer> map) {
        // 将key加入到过程中的sum中。方法中的sum对应的是从根节点到key这个位置的路径和
        sum += map.get(root);
        int nextLeft = (root / 10 + 1) * 10 + (root % 10) * 2 - 1;
        int nextRight = (root / 10 + 1) * 10 + (root % 10) * 2;
        // 遇到叶子节点，将sum加入total中
        if (!map.containsKey(nextLeft) && !map.containsKey(nextRight)) {
            total += sum;
            return;
        }
        // 如果还有左子节点，继续
        if (map.containsKey(nextLeft)) {
            helper(nextLeft, sum, map);
        }
        // 如果还有右子节点，继续
        if (map.containsKey(nextRight)) {
            helper(nextRight, sum, map);
        }
    }
}
