package org.gnuhpc.bigdata.leetcode.solutions;


import com.google.inject.internal.util.$ImmutableList;
import org.junit.Test;
import scala.math.Ordering;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/19
 */
public class GeneratePalindromes267 {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();

        List<Integer> oddIdx = new ArrayList<>();
        List<Integer> evenIdx = new ArrayList<>();

        int[] map = new int[128]; //ASCII码128个

        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            map[arr[i] - '!']++; //为什么是感叹号? 因为是ASCII码的第一个非空格的可见字符.
        }

        //Deal with the case "aaaaaaaaaaaaaa"
        //START
        int count = 0;
        for (int n : map) if (n > 0) count++;

        if (count == 1) {
            res.add(s);
            return res;
        }
        //END

        for (int i = 0; i < map.length; i++) {
            int n = map[i];
            if (n % 2 == 1) {
                oddIdx.add(i);
            }
            else {
                evenIdx.add(i);
            }
        }

        if (oddIdx.size() > 1) return res;

        String midChar = "";
        StringBuilder sb = new StringBuilder();
        for (int i : oddIdx) {
            int m = map[i];
            midChar = String.valueOf((char) (i + '!'));
            for (int j = 0; j < m - 2; j++) {
                sb.append((char) (i + '!'));
            }
        }


        for (int i : evenIdx) {
            int m = map[i];
            for (int j = 0; j < m / 2; j++) {
                sb.append((char) (i + '!'));
            }
        }

        Set<String> set = new HashSet<>();
        permutation(sb.toString(), new boolean[sb.length()], new StringBuilder(), set);

        for (String str : set) {
            res.add(str + midChar + new StringBuilder(str).reverse().toString());
        }

        return res;
    }

    private void permutation(String str,
                             boolean[] visited,
                             StringBuilder sb,
                             Set<String> set) {
        if (sb.length() == str.length()) {
            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(str.charAt(i));
                permutation(str, visited, sb, set);
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }
    }


    @Test
    public void test() {
        System.out.println(generatePalindromes("aabbccc"));
    }
}
