package org.gnuhpc.bigdata.algorithm.search;

/*核心就是不断缩小范围猜, O(logn)
T(n) = T(n/2) + O(1) = O(logn)-->通过O(1)的时间，把规模为n的问题变为n/2

首先要确定二分的范围

// 二分法四要素

start + 1 < end
start + (end - start) / 2
Sample[mid] ==, <, >
Sample[start] Sample[end] ? target

比O(n)更优的时间复杂度
几乎只能是O(logn)的二分法
经验之谈：根据时间复杂度倒推算法是面试中的常用策略


两类二分，三个境界
• 二分位置Binary search on index
    • OOXX
    • Half half
• 二分答案Binary search on result

 */

public class BinarySearch {
    public static int binarySearch(int target, int[] nums){
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        //相邻就退出
        while (start + 1 < end) {
            //不会越界
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                end = mid; //往前找
                //If you'd like to fetch the last idx of the target, use the following statement instead:
//                start = mid; //往后找
            } else if (target > nums[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }

        //范围缩小，double check
        // 最后范围内就剩下start和end两个
        if (target == nums[start]) {
            return start;
        }
        if (target == nums[end]) {
            return end;
        }
        return -1;

    }

    /*
Recursion or Non-Recursion
面试中是否使用Recursion 的几个判断条件
1. 面试官是否要求了不使用Recursion （如果你不确定，就向面试官询问）
2. 不用Recursion 是否会造成实现变得很复杂
3. Recursion 的深度是否会很深
4. 题目的考点是Recursion vs Non-Recursion 还是就是考你是否会Recursion？
• 记住：不要自己下判断，要跟面试官讨论！
     */

    // Recursive method, fetch the first idx of the target
    public static int binarySearchRecursively(int target, int[] arr){
        return helper(arr,target, 0,arr.length - 1);
    }

    private static int helper(int[] arr, int target, int l, int r) {
        if (l>r) return -1;
        int idx = (r-l)/2 + l;

        if (arr[idx] == target) return idx;
        int leftRes = helper(arr,target,l,idx-1);
        int rightRes = helper(arr, target, idx+1, r);

        if (leftRes == -1 && rightRes == -1) return -1;
        if (leftRes!=-1) return leftRes;
        if (rightRes!=-1) return rightRes;

        return -1;
    }


    public static void main(String[] args) {
        int[] array = new int[]{1,3,3,4,4,5,7,9};

        System.out.println(binarySearch(3,array));
//        System.out.println(binarySearch(9,array));
//        System.out.println(binarySearch(4,array));
//        System.out.println(binarySearch(6,array));
//        System.out.println("Another method:");
        System.out.println(binarySearchRecursively(3,array));
//        System.out.println(binarySearchRecursively(6,array));
    }
}
