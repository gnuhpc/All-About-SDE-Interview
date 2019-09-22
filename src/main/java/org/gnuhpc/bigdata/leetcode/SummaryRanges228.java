package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SummaryRanges228 {
    @Test
    public void test(){
        int[] array = new int[]{0,1,2,4,5,7};
        System.out.println(summaryRanges(array));
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int beginIdx = 0;
        int endIdx = 0;

        for (int i = 0; i < nums.length ; i++) {
            if (i == nums.length-1){ // handle the last element
                if (beginIdx == i){
                    result.add("" + nums[beginIdx]);
                } else{
                     result.add(nums[beginIdx]
                                + "->"
                                +nums[endIdx]
                        );
                }
            } else{
                if (nums[i+1] == nums[i] + 1){
                    endIdx++;

                } else {
                    if (beginIdx!=endIdx){
                        result.add(nums[beginIdx]
                                +"->"
                                +nums[endIdx]
                        );
                    } else {
                        result.add(""+nums[beginIdx]);
                    }
                    beginIdx = endIdx+1;
                    endIdx = beginIdx;
                }
            }
        }

        return result;
    }



    class Interval{
        public int start;
        public int end;

        public Interval(int init){
            start = init;
            end = init;
        }

        public String toString(){
            if(start == end){
                return start + "";
            } else {
                return start + "->" + end;
            }
        }
    }
    //Method 2: 暴力解法
    public List<String> summaryRanges2(int[] nums) {

        if (nums.length == 0) return new ArrayList<>();

        List<Interval> iList = new ArrayList<>();

        Interval interval = null;

        for(int i = 0, j = 0 ; j<nums.length;){
            if(i==j) {
                interval = new Interval(nums[i]);
                j++;
                continue;
            }

            if(nums[j]==nums[j-1]+1){ //连续就往后走
                interval.end = nums[j];
                j++;
            } else {//不连续就加入
                iList.add(interval);
                i=j;
            }
        }

        //别忘了结尾
        iList.add(interval);

        List<String> res = new ArrayList<>();

        for (Interval i: iList){
            res.add(i.toString());
        }

        return res;
    }

    // add by tina
    public List<String> summaryRanges3(int[] nums) {
        List<String> lst = new ArrayList<>();
        if(nums == null || nums.length == 0) return lst;
        int begin = nums[0];
        int end = nums[0];
        for(int i = 1;i<nums.length;i++){
            if(nums[i-1]+1 == nums[i]){
                end = nums[i];
            }
            else {
                if(begin == end) lst.add(Integer.toString(begin));
                else lst.add(begin+"->"+end);
                begin = nums[i];
                end = nums[i];
            }

        }
        if(begin == end) lst.add(Integer.toString(begin));
        else lst.add(begin+"->"+end);
        return lst;
    }

}
