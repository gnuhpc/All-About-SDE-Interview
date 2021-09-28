package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FullJustify68 {

    @Test
    public void test() {
        System.out.println(fullJustify(new String[]{
                "This", "is", "an", "example", "of", "text", "justification."
        }, 16));
//        System.out.println(fullJustify(new String[]{
//                "Listen","to","many,","speak","to","a","few."
//        },6));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        // 找到的单词的字符总数
        int charLen = 0;

        // 找到的单词个数
        int number = 0;

        // 找到的单词从什么地方开始
        int start = 0;

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (charLen + number + words[i].length() > maxWidth) {
                // 表示找到了一行，开始进行处理
                // 处理的范围是start到i，不包括i
                StringBuilder sb = new StringBuilder();
                int space = maxWidth - charLen;
                if (number > 1) {
                    for (int j = start; j < i - 1; j++) {
                        sb.append(words[j]);

                        // 然后放入对应数量的空格
                        for (int k = 0; k < space / (number - 1); k++) {
                            sb.append(' ');
                        }

                        // 判断是否需要多放一个空格
                        if (j - start + 1 <= space % (number - 1)) {
                            sb.append(' ');
                        }
                    }
                    // 放入最后一个单词
                    sb.append(words[i - 1]);
                } else {
                    // 只有一个单词，那么就采用左对齐
                    sb.append(words[start]);
                    for (int j = 0; j < space; j++) {
                        sb.append(' ');
                    }
                }
                ans.add(sb.toString());

                // 处理完毕，将值进行恢复和改变
                start = i;
                number = 1;
                charLen = words[i].length();
            } else {
                // 表示这一行还放得下单词，并改变相对应的值
                charLen += words[i].length();
                number++;
            }
        }

        // 退出循环的时候剩下所有单词全部放到一行，并且左对齐
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < words.length; i++) {
            sb.append(words[i]).append(' ');
        }
        //判断sb的长度是否是达标的
        if (sb.length() > maxWidth) {
            sb.delete(sb.length() - 1, sb.length());
        } else {
            // 填充空格
            int len = sb.length();
            for (int i = 0; i < maxWidth - len; i++) {
                sb.append(' ');
            }
        }

        ans.add(sb.toString());
        return ans;
    }

}
