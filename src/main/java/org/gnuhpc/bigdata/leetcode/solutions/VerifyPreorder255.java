package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
https://segmentfault.com/a/1190000003874375
 */
public class VerifyPreorder255 {
    /*
    对于一个搜索二叉树的前序序列来说, 如果某段序列为一个递减序列, 说明这是一段沿着左子树的路径. 直到碰到一个比前一个大的值, 说明此时已经来到某个结点的右子树上了, 而此时可以得出一个此后序列的下界值, 也就是此后序列的任意一个值必须要比这个结点的父结点的值大, 因为对于搜索二叉树来说根节点左边的都比根节点小, 而根节点右边的都比根节点大, 所以既然现在已经来到某个结点(设为A)的右子树上, 那么此后任何结点的值必然比A的值大.

那么当我们碰到一个比之前结点大的值如何找到他的父结点呢? 可以借助一个栈, 即如果当前结点比栈顶元素小, 就入栈, 如果当前值大于栈顶值, 则让所有比当前结点小的值都出栈, 直到栈顶元素比当前结点大, 则最后一个出栈的比当前结点小的值就是当前结点的父结点, 我们只要在栈元素出栈的时候更新最小下界再将当前元素入栈即可. 另外这样的时间和空间复杂度都是O(n), 但是对于空间复杂度来说, 很容易复用原数组模拟栈来优化
     */
    public boolean verifyPreorder(int[] preorder) {
        Deque<Integer> stack = new LinkedList<>();
        // 初始化最小值为最小整数
        int min = Integer.MIN_VALUE;
        for (int num : preorder) {
            // 违反最小值限定则是无效的
            if (num < min) return false;
            // 将路径中所有小于当前的数pop出来并更新最小值
            while (!stack.isEmpty() && num > stack.peek()) {
                min = stack.pop();
            }
            // 将当前值push进去
            stack.push(num);
        }
        return true;
    }
    /*
    如果序列只有一个元素，那么肯定是正确的，对应只有一个节点的树；
如果多于一个元素，以当前节点为根节点；并从当前节点向后遍历，直到大于根节点的节点出现（或者到尾巴），那么根节点之后，该大节点之前的，是左子树；该大节点及之后的组成右子树；递归判断左右子树即可；
那么什么时候一个序列肯定不是一个preorder序列呢？前面得到的右子树，如果在其中出现了比根节点还小的数，那么就可以直接返回false了；
     */

    public boolean verifyPreorder2(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1);
    }

    private boolean helper(int[] preorder, int start, int end) {

        if (start >= end)
            return true;

        // find the first that is less than
        int val = preorder[start];
        int i = start + 1;

        while (i <= end && preorder[i] < val)
            i++;

        for (int j = i; j <= end; j++) {
            if (preorder[j] < val)
                return false;
        }

        return helper(preorder, start + 1, i - 1) && helper(preorder, i, end);
    }

    @Test
    public void test() {
        System.out.println(verifyPreorder(new int[]{5, 2, 6, 1, 3}));
    }
}
