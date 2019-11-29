package org.gnuhpc.bigdata.leetcode;

import java.util.Stack;

public class IsValidSerialization331 {
    /*
    从序列起始处开始遍历,如果遇到数值就入栈，
    如果遇到"#"号,说明该位置已经到达了叶子节点,
    这时候需要查看栈顶是否也为"#",如果是,说明栈顶的前一位置就是该叶子节点的父节点.
    因此我们将栈顶元素和栈顶的上一元素出栈,然后用"#"替代入栈,表示某一分支已经结束,
    这样操作后,最终根节点的左右孩子都会被替代为"#"，合并后栈中只剩下一个"#"，表明所有节点都已经遍历完。如果最后栈中大小不为1，说明该序列是错误的
     */
    public boolean isValidSerialization(String preorder) {
        // using a stack, scan left to right
        // case 1: we see a number, just push it to the stack
        // case 2: we see #, check if the top of stack is also #
        // if so, pop #, pop the number in a while loop, until top of stack is not #
        // if not, push it to stack
        // in the end, check if stack size is 1, and stack top is #
        if (preorder == null) {
            return false;
        }
        Stack<String> st = new Stack<>();
        String[] strs = preorder.split(",");
        for (int pos = 0; pos < strs.length; pos++) {
            String curr = strs[pos];
            while (curr.equals("#") && !st.isEmpty() && st.peek().equals(curr)) {
                st.pop();
                if (st.isEmpty()) {
                    return false;//st这个时候应该有一个叶子节点的父节点，如果st已经为空则不对
                }
                st.pop();
            }
            st.push(curr);
        }
        return st.size() == 1 && st.peek().equals("#");
    }

}
