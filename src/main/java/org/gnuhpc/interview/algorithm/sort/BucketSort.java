package org.gnuhpc.interview.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {
    /**
     * 完整桶排序
     *
     * @param array 数组
     * @return sorted array
     */
    public int[] sort(int[] array) {
        //0. 计算最大值最小值划清边界
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }

        List<List<Integer>> bucketList = new LinkedList<>();
        //1. 计算桶个数 ，然后建桶
        int length = array.length + 1;
        for (int i = 0; i <= length; i++) {
            bucketList.add(new LinkedList<>());
        }
        // 2. 将数据放入对应桶内
        for (int num : array) {
            int index = bucket(num, max, min, length);
            bucketList.get(index).add(num);
        }
        // 对每个桶内部排序（可以递归桶排序）
        for (int i = 0; i < bucketList.size(); i++) {
            List<Integer> l = bucketList.get(i);
            Collections.sort(l);
            bucketList.set(i, l);
        }
        // 将排序后的桶放回数组
        int k = 0;
        for (List<Integer> temp : bucketList) {
            //遍历每一个桶的内部，统统拿出来放到该放的位置
            for (Integer integer : temp) {
                array[k] = integer;
                k++;
            }
        }
        return array;
    }

    private int bucket(int num, int max, int min, int length) { //注意这个归一化有三个要点：
        //1. 先进行除法进行归一
        //2. 转换为double
        //3. 乘以length
        return (int) (((double) (num - min) / (max - min)) * length);
    }

    @Test
    public void test() {
        int[] data = {5, 3, 0, 2, 4, 8, 9, 13, 1, 0, 6, 2, 3, 1, 12, 100, 200, 300};
        System.out.println("Before: " + Arrays.toString(data));
        sort(data);
        System.out.println("After:  " + Arrays.toString(data));
    }
}
