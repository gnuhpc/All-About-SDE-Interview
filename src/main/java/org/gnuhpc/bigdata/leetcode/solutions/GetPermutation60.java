package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

public class GetPermutation60 {
    private int    counter = 0;
    private String res     = "";

    public String getPermutation(int n, int k) {
        if (n == 1 && k == 1) return 1 + "";

        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }

        dfs(nums, new boolean[n], new StringBuilder(), k);

        return res;
    }

    private void dfs(int[] nums, boolean[] visited, StringBuilder sb, int k) {
        if (res != "") return;
        if (sb.length() == nums.length) {
            counter++;
            if (counter == k) {
                res = sb.toString();
            }
            return;
        }


        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            sb.append(nums[i]);
            dfs(nums, visited, sb, k);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    @Test
    public void test() {
        System.out.println(getPermutation(4, 9));
    }
}
