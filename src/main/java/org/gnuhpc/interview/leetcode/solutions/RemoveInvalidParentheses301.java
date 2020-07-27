package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

public class RemoveInvalidParentheses301 {
    /*
    Method1: DFS

    We all know how to check a string of Parentheses is valid using a stack.
    Or even simpler use a counter.The counter will increase when it is ( and decrease when it is ).
    Whenever the counter is negative, we have more ) than ( in the prefix.
    To make the prefix valid, we need to remove a ).
    The problem is: which one? The answer is any one in the prefix.
    However, if we remove any one, we will generate duplicate results,
    for example: s = ()), we can remove s[1] or s[2] but the result is the same ().
    Thus, we restrict to remove the first ) in a series of consecutive )s.

    After the removal, the prefix is then valid.
    We then call the function recursively to solve the rest of the string.
    However, we need to keep another information: the last removal position.
    If we do not have this position, we will generate duplicate by removing two )
    in two steps only with a different order.
    For this, we keep tracking the last removal position and only remove ) after that.

    Now one may ask. What about (? What if s = (()(() in which we need remove (?
    The answer is: do the same from right to left.
    However a cleverer idea is: reverse the string and reuse the code!
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        for (int counter = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) counter++;
            if (s.charAt(i) == par[1]) counter--;
            if (counter >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    /*
    Method2 :BFS //BFS 暴力法
    The idea is straightforward, with the input string `s`,
    we generate all possible states by removing one `(` or `)`,
    check if they are valid, if found valid ones on the current level,
    put them to the final result list and we are done,
    otherwise, add them to a queue and carry on to the next level.

    The good thing of using BFS is that
    we can guarantee the number of Parentheses that need to be removed is minimal,
    also no recursion call is needed in BFS.
    */

    public List<String> removeInvalidParentheses2(String s) {
        List<String> res = new ArrayList<>();

        // sanity check
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                // found an answer, add to the result
                res.add(s);
                found = true;
            }

            //最小数量的，发现valid的话，下边的遍历树就不用遍历了
            if (found) continue;

            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren for 输入可能包含了除 ( 和 ) 以外的字符。
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String t = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                    System.out.println(t);
                }
            }
            System.out.println();
        }

        return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')') count--;

            if (count < 0) return false;
        }

        return count == 0;
    }

    @Test
    public void test() {
        System.out.println(removeInvalidParentheses2("()())()"));
    }
}
