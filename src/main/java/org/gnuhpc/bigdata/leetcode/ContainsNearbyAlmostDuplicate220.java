package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ContainsNearbyAlmostDuplicate220 {
    //利用了Treeset这个数据结构
    /*
    * 用于对元素排序的有序集合类，集合中的元素是自然排序的，也不能有重复的元素
    * subSet(E fromElement,true, E toElement, true) 方法用于返回这个集合，其元素范围从fromElement(包括)到toElement(包括)的部分视图。
    * */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums == null || nums.length == 0 || t < 0)
            return false;
        TreeSet<Long> ts = new TreeSet<>();
        for(int i = 0; i < nums.length; ++i) {
            if(i > k)
                ts.remove((long)nums[i - k - 1]);
            TreeSet<Long> temp = (TreeSet<Long>) ts.subSet((long)nums[i] - t, true,(long)nums[i] + t, true);
            if(!temp.isEmpty())
                return true;
            ts.add((long)nums[i]);
        }
        return false;
    }

    @Test
    public void test(){
        int[] array = new int[]{10,15,18,24};
        int k = 3, t=3;

        System.out.println(containsNearbyAlmostDuplicate(array,k,t));
    }
}
