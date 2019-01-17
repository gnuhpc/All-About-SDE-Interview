package org.gnuhpc.bigdata.leetcode;

//利用了快慢指针的思想，慢指针用来标记不超过2次重复的数字应该存储的位置，快指针用来遍历数组。
//快指针应该与他的前一个数字进行对比，以发现重复数字。
public class RemoveDuplicatesLeftTwo80 {
    public static int removeDuplicates(int[] nums){
        final int k = 2;  // k用于记录数组中重复数字最多出现的次数
        int start = 1;
        int count = 1;

        if (nums == null){
            return 0;
        }

        if (nums.length<=2){
            return nums.length;
        }

        for(int i = 1; i < nums.length; ++i) {
            if(nums[i] == nums[i - 1]) { //注意这个比较条件，是和临近的上个一个比较
                if(count < k) {
                    nums[start++] = nums[i];  // 如果出现次数小于规定，慢指针后移
                }
                count++;
            } else {
                count = 1;
                nums[start++] = nums[i];
            }
        }
        return start;

    }

    public static void main(String[] args){

        int[] nums = new int[]{1,1,1,1,2,2,3};

        int size = removeDuplicates(nums);

        System.out.println("The result size is: " + size);

        for (int i = 0; i < size; i++) {
            System.out.println(nums[i]);
        }
    }

}
