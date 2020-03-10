package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.unionfind.QuickUnion;

import java.util.*;

public class NumSimilarGroups839 {
    public int numSimilarGroups(String[] A) {
        if (A == null || A.length == 0) return 0;


        //这一点有点不理解，用List包一层竟然是381ms，而直接用String数组A则是588毫秒，其余逻辑都一样
        //都可以通过
        List<String> list = new ArrayList<>(Arrays.asList(A));
        QuickUnion qu = new QuickUnion(list.size());

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                //注意如果以前没有连
                if (!qu.isConnected(i, j) && isSimilar(list.get(i), list.get(j))) {
                    qu.union(i, j);
                }
            }
        }

        return qu.count();
    }

    private boolean isSimilar(String str1, String str2) {
        int count = 0;

        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) count++;
            if (count > 2) {
                return false;
            }
        }

        return true;
    }
}
