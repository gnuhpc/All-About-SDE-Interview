package org.gnuhpc.bigdata.algorithm.search;

//查找最大/小的第k个数字
public class QuickSelect {

	private int[] nums;

	public QuickSelect(int[] nums) {
		this.nums = nums;
	}

	public int select(int k) {
		if(!(k>=1 && k<=nums.length)) return -1;
		//Step1: 圈定quickselect范围 0 , len - 1, 求第k-1的数
		// （因为是从0开始记录，求第0小的就是求最小）
        return quickselect(0, nums.length - 1, k);
	}

	private int partition(int low, int high) {
        //优化点
        //swap(low , (int)(Math.random()*(high-low+1))+low );
		int pivotIdx = low;
		int pivot = nums[pivotIdx];
		int p = low+1;
		int q = high;
		while(p <= q){
		    //小的放前边就是求最kth smallest，如果小的放后边就是求kth biggest
			while(p <= q && nums[p] < pivot) p++;
			while(p <= q && nums[q] >= pivot) q--;
			if(p < q){
				swap(p,q);
			}
		}
		//这个时候q就是pivot应该的位置，swap一下
		swap(pivotIdx,q);
		return q;
	}

	private int quickselect(int indexFirst, int indexLast, int k) {
	    // Step2: 分区算出一个idx
		int idx = partition(indexFirst, indexLast);

		// Step3： 判断这个idx和k 的关系，如果相等，说明正好
        //如果idx > k -1  ,说明左半边有正确答案
        if (idx > k - 1) {
			return quickselect(indexFirst, idx-1, k);
            //如果idx < k - 1 ,说明右半边有正确答案
        }
        else if (idx < k - 1) {
			return quickselect(idx+1, indexLast, k);
		}

        return nums[k - 1];
	}

	private void swap(int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
        System.out.println(new QuickSelect(new int[]{6, 3, 4, 5, 7, 9}).select(4));
	}
}
