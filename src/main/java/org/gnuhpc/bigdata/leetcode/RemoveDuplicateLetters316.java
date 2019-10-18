package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Copyright gnuhpc 19-8-12
 */
public class RemoveDuplicateLetters316 {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) return s;

        int[] map = new int[26];
        char[] strArr = s.toCharArray();

        for (char c : strArr) {
            map[c - 'a']++;
        }

        int minPos = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(minPos)) minPos = i; //找到字典序更小的就更新
            //如果都用完了某个字母，则这个就是当前最小的开头
            if (--map[s.charAt(i) - 'a'] == 0) break;
        }

        //递归进行，需要把这个已经列入结果的字母去掉
        return s.charAt(minPos) + removeDuplicateLetters(
                s.substring(minPos+1).replace(
                        "" + s.charAt(minPos),
                        ""
                ));
    }

    /*
    Method2 : 非递归， 发现不对再删除
    https://www.youtube.com/watch?v=SrlvMmfG8sA
     */
    public String removeDuplicateLetters2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] res = new int[26];
        boolean[] visited = new boolean[26];
        char[] ch = s.toCharArray();
        for (char c : ch) {
            res[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        int idx;
        for (char c : ch) {
            idx = c - 'a';
            res[idx]--;
            if (visited[idx]) {
                continue;
            }

            while (sb.length() > 0 && c < sb.charAt(sb.length() - 1) && res[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                visited[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(c);
            visited[idx] = true;
        }
        return sb.toString();
    }


    @Test
    public void test() {
//        System.out.println(removeDuplicateLetters("cbacdcbc"));
//        System.out.println(removeDuplicateLetters("baab"));
        System.out.println(removeDuplicateLetters("bbcaac"));
    }
}
