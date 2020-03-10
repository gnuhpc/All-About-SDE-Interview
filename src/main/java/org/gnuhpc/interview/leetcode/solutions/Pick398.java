package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 2019/9/22
 */
//https://www.youtube.com/watch?v=a9Dfp6UT2kI
// https://www.cnblogs.com/snowInPluto/p/5996269.html
    /*
    蓄水池采样算法是为了得到一个等概率的采样，具体的算法的过程：
    假设数据序列的规模为 n，需要采样的数量的为 k。

    首先构建一个可容纳 k 个元素的数组，将序列的前 k 个元素放入数组中。
    然后从第 k+1 个元素开始，以 k/n 的概率来决定该元素是否被替换到数组中（数组中的元素被替换的概率是相同的）。
    当遍历完所有元素之后，数组中剩下的元素即为所需采取的样本。
     */
public class Pick398 {
    private int[] nums;

    public Pick398(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        Random r = new Random();
        int count = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == target) {
                //我们的目标对象中选取。
                count++;
                //当count为1 也就是只有一个数值target或者我们以1/count的概率留下该数据
                if (count == 1 || r.nextInt() % count == 0) index = i;
            }
        return index;
    }

    public static void main(String[] args) {
        Pick398 s = new Pick398(new int[]{1, 2, 3, 4, 5, 4, 4, 4, 3});
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            res.add(s.pick(4));
        }

        System.out.println(res.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }
}
