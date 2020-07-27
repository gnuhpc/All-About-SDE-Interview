package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CountSmaller315 {
    //Method1 : BinarySearch ,利用二分法构建一个新数组，插入新数字, 这个数字在新数组的idx就是前边有多少个比自己小的
    // For each n in the nums, binary search its index, from the end of nums to the start,
    // and copy the result into another list.  O(nlogn)
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> sorted = new ArrayList<>(nums.length); //这里面为后边的add by idx做了一个优化
        if (nums == null || nums.length == 0) return sorted;
        Integer[] ans = new Integer[nums.length];
        sorted.add(0, nums[nums.length - 1]);
        ans[nums.length - 1] = 0;
        //题目要求看后边有多少比它小的，因此遍历的时候从反方向能一览无余。
        for (int i = nums.length - 2; i >= 0; i--) {
            int index = findIndex(sorted, nums[i]);
            ans[i] = index;
        }
        return Arrays.asList(ans);
    }

    public int findIndex(List<Integer> sorted, int cur) {
        int idx = -1;
        int start = 0, end = sorted.size() - 1;

        // 不为空就先判断两端
        //最后一个就小于cur，则放在最后
        if (sorted.get(end) < cur) idx = end + 1;
        //最前一个就大于cur，则放在最前
        if (sorted.get(start) >= cur) idx = start;
        if (idx == -1) {
            //二分找最后的位置
            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (sorted.get(mid) < cur) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
            //判断临界
            idx = (sorted.get(start) >= cur) ? start : end;
        }

        //插入新数组，cost比较大，可能牵扯到
        sorted.add(idx, cur);
        return idx;
    }

    //Method2: BST 构建中遍历，这两个方法本质一样 注意方法2比方法1快，1慢的地方在add by idx这个操作
    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        res.add(0);
        for (int i = nums.length - 2; i >= 0; i--) {
            int count = insertNode(root, nums[i]);
            res.add(count);
        }
        Collections.reverse(res);
        return res;
    }

    public int insertNode(TreeNode root, int val) {
        int count = 0;
        while (true) {
            if (val <= root.val) {
                root.count++;
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                count += root.count;
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return count;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        int count = 1;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //Method 3: Merge sort , from https://algorithm.dun.so/leetcode-315/
    // 可以先理解如下这个
    // https://medium.com/@ssbothwell/counting-inversions-with-merge-sort-4d9910dc95f0
    public List<Integer> countSmaller3(int[] nums) {

        List<Integer> list = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return list;
        }

        int len = nums.length;

        int[] idxs = new int[len];
        int[] count = new int[len];

        //idxs 为排序后的原数组index。例如原数组是2,1 则排序后的idxs为1,0
        for (int i = 0; i < len; i++)
            idxs[i] = i;

        mergeSort(nums, idxs, 0, len, count);

        for (int i : count)
            list.add(i);

        return list;
    }

    private void mergeSort(int[] nums, int[] idxs, int start, int end, int[] count) {
        if (start + 1 >= end)
            return;

        int mid = (end - start) / 2 + start;
        mergeSort(nums, idxs, start, mid, count);
        mergeSort(nums, idxs, mid, end, count);

        merge(nums, idxs, start, end, count);
    }

    private void merge(int[] nums, int[] idxs, int start, int end, int[] count) {
        int mid = (end - start) / 2 + start;

        int[] tmpIdx = new int[end - start];
        int i = start, j = mid, k = 0;

        while (i < mid && j < end) {
            if (nums[idxs[i]] > nums[idxs[j]]) {
                tmpIdx[k++] = idxs[j++];
            } else {
                count[idxs[i]] += j - mid;
                tmpIdx[k++] = idxs[i++];
            }
        }

        while (i < mid && k < end - start) {
            count[idxs[i]] += j - mid;
            tmpIdx[k++] = idxs[i++];
        }

        while (j < end && k < end - start) {
            tmpIdx[k++] = idxs[j++];
        }

        System.arraycopy(tmpIdx, 0, idxs, start, end - start);
    }


    @Test
    public void test() {
        System.out.println(countSmaller(new int[]{5, 2, 6, 1}));
    }

}
