package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2020/7/7
 */
public class BraceExpansionII1096 {
    public List<String> braceExpansionII(String expression) {
        Queue<String> queue = new LinkedList<>();
        queue.add(expression);

        Set<String> res = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            // 拿到需要处理的表达式
            String exp = queue.poll();

            // 如果表达式中没有 {，则将这个表达式加入结果中
            if (exp.indexOf("{") == -1) {
                res.add(exp);
                continue;
            }

            // 找到表达式中第一对 {}， 注意{{},{}} 找到的是pos为1,2 这一对
            int i = 0;
            int left = 0;
            int right = 0;
            while (exp.charAt(i) != '}') {
                if (exp.charAt(i) == '{') left = i;
                i++;
            }
            right = i;

            // 拿到第一对括号中的前面部分 (不包括 {)
            String before = exp.substring(0, left);
            // 拿到第一对括号中的后面部分 (不包括 })
            String after = exp.substring(right + 1);
            // 按照 , 分割第一对括号中的元素 (不包括 {})
            String[] strs = exp.substring(left + 1, right).split(",");

            // 将 before 、 strs 中的每个元素以及 after 拼接成字符串放入到队列中，方便后面处理
            for (String str : strs) {
                sb.setLength(0);
                queue.add(sb.append(before).append(str).append(after).toString());
            }
        }
        // 结果处理
        List<String> ans = new ArrayList<>(res);
        Collections.sort(ans);
        return ans;
    }

    @Test
    public void test() {
        System.out.println(braceExpansionII(
                "{{a,b},{b,c}}"));
    }


}
