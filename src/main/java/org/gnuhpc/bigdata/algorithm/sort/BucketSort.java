package org.gnuhpc.bigdata.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {
    //Simple version, waste of memory if max value iss too big while the size of sort is limited
    public void sort1(int[] a) {
        int max = a[0];
        for(int i: a){
            max = Math.max(i,max);
        }
        int[] bucket=new int[max+1];

        for (int i=0; i<bucket.length; i++) {
            bucket[i]=0;
        }

        for (int n : a) {
            bucket[n]++;
        }

        //展开结果
        int idx =0;
        for (int i=0; i<bucket.length; i++) {
            for (int j=0; j<bucket[i]; j++) {
                a[idx++]=i;
            }
        }
    }

    /**
     * 完整桶排序
     * @param array 数组
     * @return sorted array
     */
    public int[] sort2( int[] array){
        //0. 计算最大值最小值划清边界
        int max = array[0];
        int min = array[0];
        for(int i=1; i<array.length; i++){
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }

        List<List<Integer>> bucketList = new LinkedList<>();
        //1. 计算桶个数 ，然后建桶
        int length = array.length+1;
        for( int i = 0 ; i <= length ; i++ ){
            bucketList.add(new LinkedList<>());
        }
        // 2. 将数据放入对应桶内
        for (int anArray : array) {
            int index = hashcode(anArray, max, min, length);
            bucketList.get(index).add(anArray);
        }
        // 对每个桶内部排序（可以递归桶排序）
        for( int i = 0 ; i < bucketList.size() ; i++ ){
            List l = bucketList.get(i);
            Collections.sort(l);
            bucketList.set(i, l);
        }
        // 将排序后的桶放回数组
        int k = 0;
        for (List<Integer> temp : bucketList) {
            for (int j = 0; j < temp.size(); j++) {
                array[k] = temp.get(j);
                k++;
            }
        }
        return array;
    }

    private int hashcode(int num, int max, int min, int length){ //注意这个归一化
        return (int)(((double)length/(max-min))*(num-min));// length*归一化
    }

    @Test
    public void test(){
        int [] data= {5,3,0,2,4,1,0,5,2,3,1,4};
        System.out.println("Before: " + Arrays.toString(data));
        sort1(data);
        System.out.println("After:  " + Arrays.toString(data));

        int [] data2= {5,3,0,2,4,8,9,13,1,0,6,2,3,1,12,100,200,300};
        System.out.println("Before: " + Arrays.toString(data2));
        sort2(data2);
        System.out.println("After:  " + Arrays.toString(data2));
    }
}
