package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright gnuhpc 2019/11/6
 */
public class FindFrequentTreeSum508 {
    private int maxCount;

    private Map<Integer, Integer> counter = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        if (null == root) {
            return new int[0];
        }
        postOrder(root);
        List<Integer> collector = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == maxCount) {
                collector.add(entry.getKey());
            }
        }
        int[] res = new int[collector.size()];
        int index = 0;
        for (Integer i : collector) {
            res[index++] = i;
        }
        return res;
    }

    private int postOrder(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = postOrder(root.left);
        int right = postOrder(root.right);
        int key = left + right + root.val;
        int val = counter.getOrDefault(key, 0) + 1;
        counter.put(key, val);
        // 更新最大出现次数
        if (val > maxCount) {
            maxCount = val;
        }
        return key;
    }
}
