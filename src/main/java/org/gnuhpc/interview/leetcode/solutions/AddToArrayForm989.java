package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.List;

/**
 * Copyright gnuhpc 2019/12/29
 */
public class AddToArrayForm989 {
    //TODO 加法的通解
    public List<Integer> addToArrayForm(int[] A, int K) {
        int n = A.length;
        LinkedList<Integer> res = new LinkedList<>();
        int sum = 0, carry = 0;
        for (int i = n - 1; i >= 0 || K != 0; K = K / 10,i--) {  // 循环条件：两个数有一个没完
            int x = i >= 0 ? A[i]: 0;
            int y = K != 0 ? K % 10 : 0;

            sum = x + y + carry;
            carry = sum / 10;
            res.addFirst(sum%10);
        }
        if (carry != 0) res.add(0, carry);
        return res;
    }


}
