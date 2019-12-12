package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
## Time complexity
`O(n)` where `n` is the length of the input array.

## Space complexity
`O(n)`
 */

//DONE
public class TwoSum1 {
    @Test
    public void test(){
        int[] nums = new int[] {3,3};
        int target = 6;

        Utils.printArray(twoSum(nums, target));
    }

    // construct distanceMap <num, idx>
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> recordsMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int a = nums[i], b = target - nums[i];
            if(recordsMap.containsKey(b)){
                result[0] = i;
                result[1] = recordsMap.get(b);
            } else {
                recordsMap.put(a,i);
            }
        }
        return result;
    }
}
