package org.gnuhpc.bigdata.algorithm.recursion.findmedian;

import org.gnuhpc.bigdata.leetcode.utils.Utils;

/**
 * Copyright gnuhpc 19-8-10
 */
public class QuickSelect {
    public static void main(String[] args) {
        double median = 0.0;
        int[] array = Utils.generateRandomArray(9, 0, 100);
        if (array.length % 2 == 0) {
            median = (quickSelect(array, array.length / 2, 0, array.length - 1) +
                      quickSelect(array, array.length / 2 - 1, 0, array.length - 1)) / 2.0;
        }
        else {
            median = quickSelect(array, (array.length - 1) / 2, 0, array.length - 1);
        }
        Utils.printArray(array);
        System.out.print(median);
    }

    private static int quickSelect(int[] array, int pos, int left, int right) {
        if (left == right && left == pos) {
            return array[left];
        }
        int posRes = partition(array, left, right);
        if (posRes == pos) {
            return array[posRes];
        }
        else if (posRes < pos) {
            return quickSelect(array, pos, posRes + 1, right);
        }
        else {
            return quickSelect(array, pos, left, posRes - 1);
        }
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[left];
        int position = left;
        for (int i = left + 1; i <= right; i++) {
            if (array[i] <= pivot) {
                position++;
                Utils.swap(array, i, position);
            }
        }
        Utils.swap(array, left, position);
        return position;
    }

}
