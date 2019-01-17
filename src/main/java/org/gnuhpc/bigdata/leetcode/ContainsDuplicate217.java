package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate217 {
    public boolean containsDuplicate(int[] nums) {
        boolean result = false;
        Map<Integer,Integer> records = new HashMap<>();

        for (int i :nums){
            records.put(i,records.getOrDefault(i,0)+1);
            if (records.get(i)>1){
                result = true;
                break;
            }
        }

        return result;
    }
}
