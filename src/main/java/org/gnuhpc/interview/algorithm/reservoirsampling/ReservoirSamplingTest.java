package org.gnuhpc.interview.algorithm.reservoirsampling;

import org.junit.Before;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Copyright gnuhpc 2019/10/2
 */

/*
from https://www.cnblogs.com/snowInPluto/p/5996269.html
 */

/*
用来解决如下问题。
给定一个数据流，数据流长度N很大，
且N直到处理完所有数据之前都不可知，
请问如何在只遍历一遍数据（O(N)）的情况下，能够
等概率的随机选取出k个不重复的数据。
 */
/*
算法思路大致如下：

如果接收的数据量小于k，则依次放入蓄水池。
当接收到第i个数据时，i >= k，在[0, i]范围内取以随机数d，若
d的落在[0, k-1]范围内，则用接收到的第i个数据替换蓄水池中的第d个数据。
重复步骤2。
算法的精妙之处在于：当处理完所有的数据时，蓄水池中的每个数据都是以k/N的概率获得的。
 */
/*
### Problem:
  - Choose k entries from n numbers. Make sure each number is selected with the probability of k/n

### Basic idea:
  - Choose 1, 2, 3, ..., k first and put them into the reservoir.
  - For k+1, pick it with a probability of k/(k+1), and randomly replace a number in the reservoir.
  - For k+i, pick it with a probability of k/(k+i), and randomly replace a number in the reservoir.
  - Repeat until k+i reaches n

### Example
  - Choose 3 numbers from [111, 222, 333, 444]. Make sure each number is selected with a probability of 3/4
  - First, choose [111, 222, 333] as the initial reservior
  - Then choose 444 with a probability of 3/4
  - For 111, it stays with a probability of
    - P(444 is not selected) + P(444 is selected but it replaces 222 or 333)
    - = 1/4 + 3/4*2/3
    - = 3/4
  - The same case with 222 and 333
  - Now all the numbers have the probability of 3/4 to be picked
 */
/*
如何以某一概率来保留某个数据。假如概率是1/3，可以这样实现：

# !/usr/bin/env ruby
# coding: UTF-8

num = Random.rand(1..3)  # 生成不小于1,不大于3的随机整数
if num <=1
    puts '保留该数据'
else
    puts '不保留该数据'
end
 */
public class ReservoirSamplingTest {
    private int[] pool; // 所有数据
    private final int N = 100000; // 数据规模
    private Random random = new Random();

    @Before
    public void setUp() throws Exception {
        // 初始化
        pool = new int[N];
        for (int i = 0; i < N; i++) {
            pool[i] = i;
        }
    }

    private int[] sampling(int k) {
        int[] reservoir = new int[k];
        for (int i = 0; i < k; i++) { // 前 k 个元素直接放入数组中
            reservoir[i] = pool[i];
        }

        for (int i = k; i < N; i++) { // k + 1 个元素开始进行概率采样
            // 随机获得一个[0, i]内的随机整数
            int r = random.nextInt(i + 1);
            // 如果随机整数落在[0, k-1]范围内，则替换蓄水池中的元素
            if (r < k) {
                reservoir[r] = pool[i];
            }
        }

        return reservoir;
    }

    @Test
    public void test() throws Exception {
        for (int i : sampling(100)) {
            System.out.println(i);
        }
    }
}
