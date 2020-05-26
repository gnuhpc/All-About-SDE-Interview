package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

//明确一个不争的事实，数组是一个特殊的数组，里面的数都在[1,length-1]范围内
public class FindDuplicate287 {

    /**
     * Find the smallest m such that len(nums <= m) > m, which means m is the duplicate number.
     * <p>
     * In the sorted form [1, 2, …, m1, m2, m + 1, …, n]
     * <p>
     * There are m+1 numbers <= m
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        if (nums.length <= 1)
            return 0;
        if (nums.length == 2) {
            return nums[0];
        }
        int small = 1, big = nums.length - 1, guess;
        while (small < big) {
            // 猜测一个数字，比如中间的一个，其实猜哪个数字都无所谓！！，你改为10也行，3也行，只是效率不行
            // 猜2可以将一半的数字剔除，才是关键
            // 这也解释了为什么二分法有时候不见得非要有序。一次可以排除一半的解法统称为二分法。
            guess = small + (big - small) / 2;
            //然后计算比这个猜的数字小于等于的个数
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= guess)
                    count++;
            }
            //如果这个个数大于猜测这个数字的本身，那么猜测的范围不会超过guess
            if (count > guess)
                big = guess;
            else // 反之不会低于guess
                small = guess + 1;
        }
        return small;
    }

    // add by tina
    // 方法二：类比于链表找环快慢指针的用法

    /**
     * 假设数组中没有重复，那我们可以做到这么一点，就是将数组的下标和1到n每一个数一对一的映射起来。
     * 比如数组是213,则映射关系为0->2, 1->1, 2->3。
     * 假设这个一对一映射关系是一个函数f(n)，其中n是下标，f(n)是映射到的数。
     * 如果我们从下标为0出发，根据这个函数计算出一个值，以这个值为新的下标，
     * 再用这个函数计算，以此类推，直到下标超界。实际上可以产生一个类似链表一样的序列。
     * 比如在这个例子中有两个下标的序列，0->2->3。
     * 但如果有重复的话，这中间就会产生多对一的映射，
     * 比如数组2131,则映射关系为0->2, {1，3}->1, 2->3。这样，我们推演的序列就一定会有环路了，这里下标的序列是0->2->3->1->1->1->1->...，而环的起点就是重复的数。
     * 所以该题实际上就是找环路起点的题，和Linked List Cycle II一样。
     * 我们先用快慢两个下标都从0开始，快下标每轮映射两次，慢下标每轮映射一次，
     * 直到两个下标再次相同。这时候保持慢下标位置不变，
     * 再用一个新的下标从0开始，这两个下标都继续每轮映射一次，
     * 当这两个下标相遇时，就是环的起点，也就是重复的数。
     * 对这个找环起点算法不懂的，请参考Floyd's Algorithm。
     *
     * @param nums
     * @return
     */
    //链接：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--52/
    public int findDuplicate2(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        //寻找相遇点
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        //slow 从起点出发, fast 从相遇点出发, 一次走一步
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    @Test
    public void test() {
        System.out.println(findDuplicate(new int[]{3, 1, 3, 4, 2}));
        System.out.println(findDuplicate(new int[]{1, 1, 2}));
    }
}
