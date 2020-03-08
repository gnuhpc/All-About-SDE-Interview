package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-21 22:27
 * Version: 0.1
 */
/*
二分查找的关键点在于找到中间值以后，如何判断接下来要搜索左边半段还是右边半段。
1 2 3 4 5 6 7 可以大致分为两类,
第一类 2 3 4 5 6 7 1这种，也就是nums[start] <= nums[mid]。此例子中就是2 <= 5
这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]。则在前半部分找，
否则去后半部分找。
第二类 6 7 1 2 3 4 5这种，也就是nums[start] > nums[mid]。此例子中就是6 > 2
这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]。则在后半部分找，
否则去前半部分找。

作者：reedfan
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/ji-bai-liao-9983de-javayong-hu-by-reedfan/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Search33 {
    /*
    Method : 一次二分法
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            //总的情况分两种
            if (nums[start] < nums[mid]) {
                //1. target在 nums[start] 和 nums[mid] 之间，即符合这个上升趋势
                // 例如 2,3,4,5,6,7,1,target=4, nums[start] = 2, nums[mid] = 5
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid; //查找左半边

                    //2. target不在 nums[start] 和 nums[mid] 之间
                    // 例如 2,3,4,5,6,7,1,target=7, nums[start] = 2, nums[mid] = 5
                }
                else {
                    start = mid; //查找右半边
                }
            }
            else {
                // 例如 6,7,1,2,3,4,5  nums[end] = 6, nums[mid] = 2
                //分两种情况
                //1. target在 nums[mid] 和 nums[end] 之间，即符合这个上升趋势
                // target = 3
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid; //查找右半边
                }
                else {
                    end = mid; //查找左半边
                }
            }
        } // while

        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    /*
    Method2:  找到转折点然后分段二分。 更推荐的方法
     */
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int partition = findPartition(nums);
        if (partition != -1) {
            int partitionNum = nums[partition];
            if (target >= nums[0] && target <= nums[partition - 1]) {
                return binarySearch(nums, 0, partition - 1, target);
            }
            else {
                return binarySearch(nums, partition, nums.length - 1, target);
            }
        }
        else {
            return binarySearch(nums, 0, nums.length - 1, target);
        }
    }

    private int findPartition(int[] nums) {
        int idx = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                idx = i;
                break;
            }
        }

        return idx;
    }

    private int binarySearch(int[] nums, int l, int r, int target) {
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] < target) {
                l = m;
            }
            else {
                r = m;
            }
        }

        if (nums[l] == target) return l;
        if (nums[r] == target) return r;
        return -1;
    }

    @Test
    public void test() {
        System.out.println(search2(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }
}
