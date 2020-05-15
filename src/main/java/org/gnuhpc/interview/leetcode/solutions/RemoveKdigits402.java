package org.gnuhpc.interview.leetcode.solutions;

import java.util.Deque;
import java.util.LinkedList;

/*
 *  If there are digits who are greater than the next one, delete the first digit.
 *  If all digits in the number are increasingly sorted, delete the last digit gets deleted.
 *  The process repeats until the required k digits are deleted.
 * */

public class RemoveKdigits402 {
    public String removeKdigits(String num, int k) {
        /**
         使用栈来暂存结果的值, 只要栈不为空且栈顶元素大于下一位的值就将栈顶元素舍弃并将k减1, 然后将新的较小值加入栈内(注意前导0情况要跳过)
         最后要考虑升序序列这种情况, k最后不为0, 需要将栈顶元素删除k次, 可以进一步用一个char数组来模拟栈来优化时间效率
         **/
        int n = num.length();
        if (k == num.length()) return "0";
        Deque<Character> st = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            char ch = num.charAt(i);
            while (!st.isEmpty() && st.peek() > ch && k > 0) {
                st.pop();
                k--;
            }
            if (st.isEmpty() && ch == '0')
                continue;
            st.push(ch);
        }
        while (k-- != 0)
            st.pop();
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty())
            sb.append(st.pop());
        sb.reverse();
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
