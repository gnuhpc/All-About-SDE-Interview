package org.gnuhpc.interview.datastructure.stack;

import org.junit.Test;

import java.util.Stack;

public class StockSpan {
    public int[] findStockSpan(int[] prices) {
        int[] result = new int[prices.length];
        // Create a stack and push index of first element to it
        Stack<Integer> st = new Stack<>();
        st.push(0);

        // Span val of first element is always 1
        result[0] = 1;

        // Calculate span profits for rest of the elements
        for (int i = 1; i < prices.length; i++) {
            // Pop elements from stack while stack is not empty and top of
            // stack is smaller than price[i]
            while (!st.empty() && prices[st.peek()] <= prices[i])
                st.pop();

            // If stack becomes empty, then price[i] is greater than all elements
            // on left of it, i.e., price[0], price[1],..price[i-1]. Else price[i]
            // is greater than elements after top of stack
            result[i] = (st.empty()) ? (i + 1) : (i - st.peek());

            // Push this element to stack
            st.push(i);
        }

        return result;
    }

    @Test
    public void test() {
        int prices[] = {10, 4, 5, 90, 120, 80};

        // Fill the span profits in array S[]
        findStockSpan(prices);
    }
}
