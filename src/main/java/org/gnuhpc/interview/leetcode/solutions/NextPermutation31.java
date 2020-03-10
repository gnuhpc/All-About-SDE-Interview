package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import static org.gnuhpc.interview.leetcode.utils.Utils.swap;

/*
首先想一个问题，什么情况下求下一个排列最简单？根据题意，自然是字典序最大也就是降序排列的时候，因为这种情况的下一个排列就是字典序最小的，将原序列逆序就行了。

那么我们就围绕这个点来思考。

对于任意一个序列，我们从后往前找，先找到一个最长递减子序列，比如2 1 7 5 4 3，那么最长递减子序列就是7 5 4 3，把它逆序，变成3 4 5 7，这是最小序列，这时整个序列变成了2 1 3 4 5 7。这样还不够，我们还要满足比原序列的字典序大，其实也好处理，最长递减子序列的前一个元素是1，我们只需要在最小的序列3 4 5 7中找一个比它大并且最接近它的元素，将它换掉就可以了，显然这个替换的元素是3（替换并没有破坏最小序列的单调性），这样整个序列就变成了2 3 1 4 5 7，这也就是我们的目标序列。

作者：97wgl
链接：https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-on-si-lu-jian-dan-by-97wgl/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class NextPermutation31 {

    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == 0) {
                reverse(nums, 0, nums.length - 1);
                return;
            }
            if (nums[i] > nums[i - 1]) {
                // 将递增序列逆序
                reverse(nums, i, nums.length - 1);
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] > nums[i - 1]) {
                        swap(nums, j, i - 1);
                        return;
                    }
                }
            }
        }
    }

    private void reverse(int[] A, int i, int j) {
        while (i < j) swap(A, i++, j--);
    }

    @Test
    public void test() {
        nextPermutation(new int[]{1, 2, 3, 4});
        nextPermutation(new int[]{1, 4, 3, 2});
        nextPermutation(new int[]{1, 3, 2});
    }
}
