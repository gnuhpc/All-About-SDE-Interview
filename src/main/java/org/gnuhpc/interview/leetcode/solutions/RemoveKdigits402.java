package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

/*
 *  If there are digits who are greater than the next one, delete the first digit.
 *  If all digits in the number are increasingly sorted, delete the last digit gets deleted.
 *  The process repeats until the required k digits are deleted.
 * */
class DecreasingResult {
    public int firstDecreasing;
    public int nextStart;
}

public class RemoveKdigits402 {
    public String removeKdigits(String num, int k) {
        int remain = num.length() - k;
        char[] numArray = num.toCharArray(), res = new char[remain];
        int index = 0;
        for (int i = 0; i < numArray.length; i++) {

            //模拟stack
            while ((numArray.length - i > res.length - index) && (index > 0 && numArray[i] < res[index - 1])) index--;
            if (index < res.length) res[index++] = numArray[i];
        }

        //去开头的0
        index = 0;
        for (; index < remain; index++) {
            if (res[index] != '0') break;
        }
        String s = new String(res).substring(index);
        //空串返回"0"
        return s.length() == 0 ? "0" : s;
    }
}
