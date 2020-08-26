package org.gnuhpc.interview.leetcode.solutions;

public class NumJewelsInStones771 {
    public int numJewelsInStones(String J, String S) {
        char[] Jc = J.toCharArray();
        char[] Sc = S.toCharArray();
        int result = 0;
        int[] indexMap = new int[150];
        for (int h = 0;h<S.length();h++)
        {
            indexMap[Sc[h]]++;
        }
        for (int i = 0;i<J.length();i++)
        {
            result+=indexMap[Jc[i]];
        }
        return result;
    }
}
