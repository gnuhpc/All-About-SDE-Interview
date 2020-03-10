package org.gnuhpc.interview.algorithm.dynamicprogramming.lcs;

import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=tABtJbLOQho
public class LongestCommonSubstringDP {
    public static List<String> commonSubstring(String S1, String S2) {
        Integer[][] match = new Integer[S1.length()][S2.length()];

        int len1 = S1.length();
        int len2 = S2.length();
        int max = Integer.MIN_VALUE;
        List<String> result = new ArrayList<>();

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (S1.charAt(i) == S2.charAt(j)) {
                    if (i == 0 || j == 0) match[i][j] = 1;

                    else match[i][j] = match[i - 1][j - 1] + 1;

                    if (match[i][j] > max) {
                        max = match[i][j];
                        result.clear();//更大以后过去就不算了
                        result.add(S1.substring(i - max + 1, i + 1));
                    } else if (match[i][j] == max) {
                        result.add(S1.substring(i - max + 1, i + 1));
                    }
                } else match[i][j] = 0;

            }
        }
        return result;
    }


    public static void main(String[] args) {
        List<String> result = commonSubstring("CLCL", "LCLC");
        for (String str : result) {
            System.out.println(str);
        }
    }
}
