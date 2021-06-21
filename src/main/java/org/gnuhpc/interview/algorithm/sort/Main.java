package org.gnuhpc.interview.algorithm.sort;

import org.gnuhpc.interview.leetcode.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main{
    public static void main(String[] args)
    {
        int[] arr1 = {10, 7, 13, 1, 8, 5};
        int[] arr2 = {9, 6, 13};
        int[] arr3 = {5, 2};
        int[] arr4 = {21, 2, 6, 9, 3, 4, 8, 11};

        printRes(arr4, 3);
        printRes(arr4, 4);
        printRes(arr4, 5);
        printRes(arr1, 6);
        printRes(arr1, 5);
        printRes(arr1, 3);
        printRes(arr1, 2);
        printRes(arr2, 2);
        printRes(arr3, 2);
        printRes(arr3, 1);

    }

    public  static void printRes(int[] arr, int k) {
        System.out.print(Arrays.toString(arr) + " => ");
        for (List r : partition(arr, k)) {
            System.out.print(r);
        }
        System.out.println("; k=" + k);
    }


    public static List<ArrayList<Integer>> partition(int[] arr, int k) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>(k);
        if (k > arr.length || k < 0) {
            return resultList;
        } else if (k == arr.length) {
            for (int c : arr) {
                ArrayList<Integer> t = new ArrayList<>();
                t.add(c);
                resultList.add(t);
            }
            return resultList;
        }

        for (int i = 0; i < k; i++) {
            resultList.add(new ArrayList<>());
        }

        arr = Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();
        // 从最大的开始填充到结果中
        for (int i = 0; i < k; i++) {
            resultList.get(i).add(arr[i]);
        }
        // 从大到小遍历剩下的数字
        for (int i = k; i < arr.length; i++) {
            ArrayList<Integer> tempSum = new ArrayList<>(k);
            for (List<Integer> l : resultList) {
                tempSum.add(getSum(l) + arr[i]);
            }
            int minIndex = findMinIndex(tempSum); // 找出结果最小的那个分组
            resultList.get(minIndex).add(arr[i]); // 将当前数加入那个分组
        }

        return resultList;
    }

    /**
     * @return The index of the min member
     */
    public static int findMinIndex(ArrayList<Integer> list) {
        int res = 0;
        int t = Integer.MAX_VALUE;
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index) < t) {
                t = list.get(index);
                res = index;
            }
        }
        return res;
    }

    /**
     * @return Sum of list members
     */
    public static int getSum(List<Integer> list) {
        int res = 0;
        for (int i : list) {
            res += i;
        }
        return res;
    }

}