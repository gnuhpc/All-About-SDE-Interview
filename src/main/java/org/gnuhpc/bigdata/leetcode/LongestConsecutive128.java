package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.set.unionfindset.WeightedQuickUnion;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

    // add by tina, 借用一个Hash Set
    //使用一个集合HashSet存入所有的数字，然后遍历数组中的每个数字，
    // 如果其在集合中存在，那么将其移除，
    // 然后分别用两个变量pre和next算出其前一个数跟后一个数，
    // 然后在集合中循环查找，如果pre在集合中，那么将pre移除集合，
    // 然后pre再自减1，直至pre不在集合之中，对next采用同样的方法，
    // 那么next-pre-1就是当前数字的最长连续序列，更新res即可。
    // 这里再说下，为啥当检测某数字在集合中存在当时候，都要移除数字。
    // 这是为了避免大量的重复计算，就拿题目中的例子来说吧，
    // 我们在遍历到4的时候，会向下遍历3，2，1，如果都不移除数字的话，
    // 遍历到1的时候，还会遍历2，3，4。
    // 同样，遍历到3的时候，向上遍历4，向下遍历2，1，等等等。
    // 如果数组中有大量的连续数字的话，那么就有大量的重复计算，
    // 十分的不高效，所以我们要从HashSet中移除数字
    public int longestConsecutive3(int[] nums) {
        int res = 0;
        Set<Integer> s = new HashSet<Integer>();
        for (int num : nums) s.add(num);
        for (int num : nums) {
            if (s.remove(num)) {
                int pre = num - 1, next = num + 1;
                while (s.remove(pre)) --pre;
                while (s.remove(next)) ++next;
                res = Math.max(res, next - pre - 1);
            }
        }
        return res;
    }



    @Test
    public void test(){
        int ret = longestConsecutive2(new int[]{100,4,200,1,3,2});
        assertEquals(ret,4);
    }
}
