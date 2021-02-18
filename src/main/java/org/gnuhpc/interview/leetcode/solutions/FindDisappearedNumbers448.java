package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindDisappearedNumbers448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        //用来存放结果
        List<Integer> res = new ArrayList<>();
        //1. 遍历下数组的元素，对对应的索引位置的元素作标记
        int len = nums.length;
        for(int i = 0; i < len; i++){
            int num = Math.abs(nums[i]);  //由于数组的元素有可能被*-1，所以取绝对值
            int index = num - 1;
            if(nums[index] > 0){
                nums[index] *= -1;
            }
        }
        // 寻找没有标记的索引位置
        for(int i = 0; i < len; i++){
            if(nums[i] > 0){
                res.add(i+1);
            }
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
