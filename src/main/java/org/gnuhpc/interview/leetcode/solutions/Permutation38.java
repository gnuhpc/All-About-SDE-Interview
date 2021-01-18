package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Permutation38 {
    public String[] permutation(String s) {
        List<String> resList = new LinkedList<>();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        boolean[] isVisited = new boolean[arr.length];
        dfs(arr, resList, new StringBuilder(),isVisited);
        String[] res = new String[resList.size()];
        int i = 0;
        for(String str: resList){
            res[i++] = str;
        }

        return res;
    }

    private void dfs(char[] arr, List<String> res, StringBuilder sb, boolean[] isVisited){
        if(sb.length() == arr.length) {
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(isVisited[i]) continue;

            if(i>0 && arr[i]==arr[i-1] && !isVisited[i-1]){
                continue;
            }

            sb.append(arr[i]);
            isVisited[i] = true;
            dfs(arr, res, sb, isVisited);
            isVisited[i] = false;
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
