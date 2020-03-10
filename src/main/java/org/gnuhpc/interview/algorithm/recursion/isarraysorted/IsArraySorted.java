package org.gnuhpc.interview.algorithm.recursion.isarraysorted;

public class IsArraySorted {
    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 4, 5, 6, 1};
        int[] array2 = new int[]{1, 3, 4, 5, 6};
        int[] array3 = new int[]{1, 3, 4, 5, 6, 1, 5, 2};

        System.out.println(isArraySorted(array));
        System.out.println(isArraySorted(array2));
        System.out.println(isArraySorted(array3));

    }

    private static boolean isArraySorted(int[] array) {
        return helper(array, 0);
    }

    private static boolean helper(int[] array, int start) {
        if (start + 1 == array.length) {
            return true;
        }

        if (array[start] >= array[start + 1]) {
            return false;
        } else {
            return helper(array, start + 1);
        }
    }
}
