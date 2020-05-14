package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

public class SingleNumber137 {
    /*
    将每个数想象成32位的二进制，对于每一位的二进制的1和0累加起来必然是3N或者3N+1，
    为3N代表目标值在这一位没贡献，3N+1代表目标值在这一位有贡献(=1)，然后将所有有贡献的位|起来就是结果。
    这样做的好处是如果题目改成K个一样，只需要把代码改成cnt%k，很通用
     */
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            int cnt = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & mask) != 0) {
                    cnt++;
                }
            }
            if (cnt % 3 != 0) {
                ret |= mask;
            }
        }
        return ret;
    }

    public int singleNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int n : nums) {
            //该判断语句的整体作用是：如果当前数字（nums[i]）已经在之前出现过，那么在哈希集实例（set）中移除当前数字
            // Add 方法的作用是添加当前数字于哈希集中，如果当前数字和该集合（set）元素存在重复，则返回 False 。故在此采用了逻辑非操作符（!）
            if (!set.add(n))
                set.remove(n);
        }
        //因为每个重复元素最多存在两个，而重复元素的第一个添加后均被移除，而第二个均未添加成功，故此时哈希集只保留唯一且未重复的元素
        // First*1 方法的作用是返回该序列的第一个元素
        return set.iterator().next();

    }
}
