package org.gnuhpc.bigdata.algorithm.sort;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

//O(n+k), k是最大值和最小值的差值
//和桶排序、基数排序的差别： https://stackoverflow.com/questions/14368392/radix-sort-vs-counting-sort-vs-bucket-sort-whats-the-difference
/*
首先记住一个顺序，计数排序、基数排序、桶排序
然后明白计数排序的缺点是最大值最小值的差再+1为桶的个数，但是会非常浪费内存，
比如这个例子最大值最小值为99，也就是需要100个桶。
我们可以优化下，使用分别对某一位进行排序的方式进行计数排序--这就是基数排序。
最后我们泛化下，bucket是一个子区域，这就是桶排序。
Counting sort -- simple buckets, simple processing, memory overhead
Radix sort -- simple buckets, sophisticated processing, speed overhead (and still need additional static memory)
Bucket sort -- sophisticated buckets, simple processing, requires dynamic memory, good in average
 */
public class CountingSort {
    @Test
    public void test(){

        int[] intArray = { 2, 5, 9, 8, 2, 8, 7, 100, 4, 3 };
        sort1(intArray);
        Utils.printArray(intArray);

        int[] intArray2 = { 2,6,5,100,200};
        sort2(intArray2);
        Utils.printArray(intArray2);
    }

    //化简版，可以直接修改输入
    public void sort1(int[] input) {
        int n = input.length;
        if (n == 0)
            return;
        /** find maximum and minimum values **/
        int max = input[0], min = input[0];
        for (int i = 1; i < n; i++) {
            if (input[i] > max)
                max = input[i];
            if (input[i] < min)
                min = input[i];
        }
        int range = max - min + 1;

        int[] count = new int[range];

        //先计数
        for (int i :input) { //减去偏移量
            count[i - min]++;
        }

        //再取idx为最终数值，arr[idx]里面记录的是次数
        for (int i = 0, j =0 ; i < count.length; i++) {
            while (count[i] > 0) {
                input[j++] = i + min;
                count[i]--;
            }
        }
    }

    //完整版： https://www.javainuse.com/java/countingsort
    //视频演示：https://www.geeksforgeeks.org/counting-sort/
    //完整版和简化版的区别在于是不是可以直接修改原数组，在一般sort中用简化版即可，
    // 但是在诸如基数排序RadixSort中就不行，因为count只记录了其中一位的情况， 如果直接覆盖原数组则数字就丢失了

    public void sort2(int[] input) {
        int n = input.length;
        /** find maximum and minimum values **/
        int max = input[0], min = input[0];
        for (int i = 1; i < n; i++) {
            if (input[i] > max)
                max = input[i];
            if (input[i] < min)
                min = input[i];
        }

        int range = max - min + 1;
        int[] output = new int[n];
        int[] count = new int[range]; //注意count数组的大小是range

        /** initialize the occurrence of each element in the count array **/
        for (int num : input) {
            count[num-min]++; //num-min是为了减少count的内存
        }

        //完整版要整理其在排序后的确定idx
        /** calculate sum of indexes **/
        //count[i] now contains actual position of this character in output array
        for (int i = 1; i < range; i++)
            count[i] += count[i - 1];

        /** modify original array according to the sum count **/
        // Build the output character array
        // To make it stable we are operating in reverse order.
        for(int i=n-1; i>=0; i--){
            output[count[input[i]-min]-1] = input[i];
            count[input[i]-min]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        System.arraycopy(output, 0, input, 0, input.length);
    }
}
