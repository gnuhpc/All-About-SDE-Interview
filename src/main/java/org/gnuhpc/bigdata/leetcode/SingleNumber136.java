package org.gnuhpc.bigdata.leetcode;

import java.util.HashSet;
import java.util.Set;

public class SingleNumber136 {
    public int singleNumber(int[] nums) {
        Set<Integer> setresult = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if(!setresult.remove(nums[i])){
                setresult.add(nums[i]);

            }
        }

        return (int) setresult.iterator().next();
    }
}
