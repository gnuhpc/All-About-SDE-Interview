package org.gnuhpc.interview.leetcode.solutions;

import java.util.Arrays;

public class MinNumber45 {
    public String minNumber(int[] nums) {
        String[] nums1 = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(nums1, ((String o1, String o2) -> {
            int a = o1.length();
            int b = o2.length();
            int i = 0, j = 0;
            while (i != a && j != b) {
                if (o1.charAt(i) == o2.charAt(j)) {
                    i++;
                    j++;
                    // 如果i到了最后但是j还没到那么让i归零
                    if (i==a && j<b){
                        i=0;
                    }
                    // 如果j到了最后但是i还没到那么让j归零
                    if (j==b && i<a){
                        j=0;
                    }
                } else if (o1.charAt(i) < o2.charAt(j)) {
                    return -1;
                } else {
                    return 1;
                }
            }
            return 0;

        }));
        StringBuilder res= new StringBuilder();
        for (int i = 0; i < nums1.length; i++) {
            res.append(nums1[i]);
        }
        return res.toString();
    }

}
