package org.gnuhpc.bigdata.algorithm.search;

import java.util.Random;

//查找最大/小的第k个数字
public class Quickselect {

	private int[] nums;

	public Quickselect(int[] nums) {
		this.nums = nums;
	}

	public int select(int k) {
		return select(0, nums.length - 1, k - 1);
	}

	private int partition(int indexFirst, int indexLast) {

		int pivot = new Random().nextInt(indexLast - indexFirst + 1) + indexFirst;		
		
		swap(indexLast, pivot);

		for (int i = indexFirst; i < indexLast; i++) {
			//if (nums[i] > nums[indexLast]) { 如果想找大的就把大的放在pivot的前边
            if (nums[i] <= nums[indexLast]) { //如果想找第n小的，则将小的放在pivot前边
				swap(i, indexFirst);
				indexFirst++;//这个变量是分界
			}
		}

		swap(indexFirst, indexLast);

		return indexFirst;
	}

	private int select(int indexFirst, int indexLast, int k) {

		int pivot = partition(indexFirst, indexLast);

		if (pivot > k) {
			return select(indexFirst, pivot - 1, k);
		} else if (pivot < k) {
			return select(pivot + 1, indexLast, k);
		}
		
		return nums[k];
	}

	private void swap(int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}


	public static void main(String[] args) {
		System.out.println(new Quickselect(new int[]{1,3,4,5,7,9}).select(2));
	}
}
