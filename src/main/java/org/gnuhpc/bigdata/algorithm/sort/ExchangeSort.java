package org.gnuhpc.bigdata.algorithm.sort;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

/*
交换排序，对比选择排序，它是发现一个可以交换的就交换，
选择排序则是找出最应该交换的那个进行最终交换

也正是由于这种无脑交换，它是最好写的，效率还可以的一种交换算法

 */
public class ExchangeSort {
    public static void sort( int[] num )
    {
        int i, j, temp;  //be sure that the temp variable is the same "type" as the array
        for ( i = 0; i < num.length - 1; i ++ )
        {
            for ( j = i + 1; j < num.length; j ++ )
            {
                if( num[i] > num[j] )         //sorting into descending order
                {
                    Utils.swap(num,i,j);
                }
            }
        }
    }

    @Test
    public void test(){
        int[] arr = Utils.generateRandomArray(10, 0, 100);
        Utils.printArray(arr);
        sort(arr);
        Utils.printArray(arr);
    }
}
