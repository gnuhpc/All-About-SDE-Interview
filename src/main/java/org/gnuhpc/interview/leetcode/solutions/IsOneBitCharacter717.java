package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/7/9
 */
public class IsOneBitCharacter717 {
    public boolean isOneBitCharacter(int[] bits) {
        //最后一位是1的一定不能是1bit 0结尾
        if (bits[bits.length - 1] == 1) return false;
        //开始遍历
        int i = 0;
        for (; i < bits.length - 1; ) {
            //前边是0的统统跳过
            if (bits[i] == 0) i += 1;
                //碰到1则一次跳过两个，因为后边不管是10还是11都能跳过
            else i += 2;
        }

        //退出的时候看最后跳的步长是1还是2：
        //i==bits.length-1 说明最后跳的步长是1，说明是1bit 0结尾
        //i==bits.length 说明最后跳的步长是2，说明不是1bit 0 结尾
        //
        return i == bits.length - 1;
    }
}
