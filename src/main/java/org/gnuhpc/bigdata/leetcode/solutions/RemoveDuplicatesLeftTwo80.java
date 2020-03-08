package org.gnuhpc.bigdata.leetcode.solutions;

//利用了快慢指针的思想，慢指针用来标记不超过2次重复的数字应该存储的位置，快指针用来遍历数组。
//快指针应该与他的前一个数字进行对比，以发现重复数字。
public class RemoveDuplicatesLeftTwo80 {
    public static int removeDuplicates(int[] nums) {
        //永远指向第一个不满足题意的位置
        int start = 1;
        int count = 1;

        if (nums == null) {
            return 0;
        }

        if (nums.length <= 2) {
            return nums.length;
        }

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1]) { //注意这个比较条件，是和临近的上个一个比较
                if (count < 2) {
                    nums[start++] = nums[i];  // 如果出现次数小于规定，慢指针后移
                }
                count++;
            }
            else {
                count = 1;
                nums[start++] = nums[i];
            }
        }
        return start;

    }

    public static void main(String[] args) {

        int[] nums = new int[]{1, 1, 1, 1, 2, 2, 3};

        int size = removeDuplicates(nums);

        System.out.println("The result size is: " + size);

        for (int i = 0; i < size; i++) {
            System.out.println(nums[i]);
        }
    }

    // add by Tina
    public int removeDuplicates2(int[] nums) {
        if (nums == null) {
            return 0;
        }

        if (nums.length <= 2) {
            return nums.length;
        }
        /*
        1,1,1,2,2,3
          i j
        */
        int i = 1; // point to previous,满足题意的数组下标
        int j = 2; // point to current,遍历全数组

        while (j < nums.length) {
            //什么情况下i停止变化，等待符合题目的新数据逐一覆盖
            if (nums[j] == nums[i] && nums[j] == nums[i - 1]) {
                j++;
            }
            else { //仅需往前赋值，无需交换，因为j一直朝前遍历，i表示有效数据
                i++;
                nums[i] = nums[j];
                j++;
            }
        }

        return i + 1;
    }

    //Method 3: Two pointers
    public int removeDuplicates3(int[] nums) {
        if (nums == null) {
            return 0;
        }

        if (nums.length <= 2) {
            return nums.length;
        }

        //永远指向第一个不满足题意的位置, 0 和 1 无论是否相同都满足题意，因此直接跳过
        int j = 2;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[j - 2]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }

}
