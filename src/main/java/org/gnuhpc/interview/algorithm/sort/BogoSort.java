package org.gnuhpc.interview.algorithm.sort;

//遍历所有组合看看是否有有序，O(!(n+1)), 但是可以学习到如何产生所有组合
public class BogoSort {

    public static void main(String[] args) {

        int[] nums = {34, -2, -3, 4, 5, 6, 67, 32, -77, 678};
        int loopCounter = 0;

        while (!isSorted(nums)) {
            ++loopCounter;
            shuffle(nums);
        }

        System.out.println("Steps reaquired to sort array: " + loopCounter);
        showArray(nums);
    }

    /*   Fisher-Yates algorithm in order to shuffle an array
     *	The algorithm produces an unbiased permutation: every permutation is equally likely
     * 		O(N) running time ~ proportional to the number of items we want to shuffle
     * 		 + in-place algorithm !!!
     */
    public static void shuffle(int[] nums) {

        for (int i = nums.length - 1; i >= 0; --i) {
            int j = (int) (Math.random() * i);
            swap(nums, i, j);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static boolean isSorted(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                return false;
            }
        }

        return true;
    }

    public static void showArray(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            System.out.print(nums[i] + " ");
        }
    }
}
