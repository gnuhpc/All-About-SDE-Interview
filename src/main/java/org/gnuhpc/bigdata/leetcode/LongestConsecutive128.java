package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.set.unionfindset.WeightedQuickUnion;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class LongestConsecutive128 {
    /* Method1 : 纯技巧性的
    Use HashMap to store val and its current length of consecutive elements(not necessarily final),
    only updating and maintain max length on boundary points(n-left & n+right).
    Complexity: O(N)
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res=0;
        for (int num:nums){
            if (map.containsKey(num)){
                continue;
            }
            //1.check if num has conseq and update len
            int left = map.getOrDefault(num - 1, 0);
            int right = map.getOrDefault(num + 1, 0);
            int len = left+right+1;
            res = Math.max(res, len);
            map.put(num, -1);  //随便赋值,标记num在map中已存在

            // 2.Union by only updating boundary(maintaining total len)
            // Leave middle k-v dirty to avoid cascading update
            map.put(num-left, len);  //左边端点
            map.put(num+right, len); //右边端点
        }
        return res;
    }

    /*
    Method 2: Union Find
     */

    public int longestConsecutive2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int res = 1;
        // Numval - idx
        Map<Integer,Integer> map = new HashMap<>();

        IntStream.range(0,nums.length).forEach(i->map.put(nums[i],i));

        WeightedQuickUnion uf = new WeightedQuickUnion(nums.length);
        for(int num : nums){
            if(map.containsKey(num - 1))
                uf.union(map.get(num),map.get(num - 1));
            if(map.containsKey(num + 1))
                uf.union(map.get(num),map.get(num + 1));
            res = Math.max(res,uf.sz[uf.find(map.get(num))]);
        }
        return res;

    }



    @Test
    public void test(){
        int ret = longestConsecutive2(new int[]{100,4,200,1,3,2});
        assertEquals(ret,4);
    }
}
