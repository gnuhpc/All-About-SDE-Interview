package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class PermuteUnique47 {

    //Method1 : DFS
/*
* 使用排列式深度优先搜索算法。
和没有重复元素的 Permutation 一题相比，只加了两句话：
Arrays.sort(nums) // 排序这样所有重复的数
if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) { continue; } // 跳过会造成重复的情况
* */

    //https://www.youtube.com/watch?v=re9JDd7M-v8 上边有个图画的不错

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null || nums.length ==0)  return res;

        boolean[] visited = new boolean[nums.length];
        List<Integer> list = new ArrayList<>();

        Arrays.sort(nums);
        dfs(nums, visited, list, res);
        return res;
    }

    public void dfs(int[] nums, boolean[] visited, List<Integer> list, List<List<Integer>> res) {
        if(list.size()==nums.length) {
            //不能直接传进去list，否则对list的改动都体现在最终结果了
            res.add(new ArrayList<>(list));
            return ;
        }
        for (int i=0; i<nums.length; i++) {
            // 当前位置的数已经在List中了 ,实际上是剪枝,
            if(visited[i]) continue; // add by tina 这一行并不会被命中吧？ 当然会命中，因为每次都是从数据集的开始进行遍历的
            // 当前元素与其前一个元素值相同 且 前元素尚没有被加到list中，跳过该元素 （如果不跳过直接加入，则不是用了第一个1就是用了第二个1，从结果看来都是重复的排列）
            if(i>0 && nums[i]==nums[i-1] && !visited[i-1])   {
                continue;//only insert duplicate element when the previous duplicate element has been inserted
            }
            // 深度优先搜索遍历
            visited[i]=true;
            list.add(nums[i]);
            dfs(nums, visited, list, res);
            list.remove(list.size()-1);
            visited[i]=false;
        }
    }

    //方法二
    private List<List<Integer>> list= new ArrayList<>();

    public void swap(int[] nums,int a,int b){
        int tmp=nums[a];
        nums[a]=nums[b];
        nums[b]=tmp;
    }

    private void dfs(int[] nums, int index){
        if(index==nums.length)
            list.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));

        Set<Integer> set = new HashSet<>(); //因为有重复，所以每次交换之和一个出现相同的元素进行交换，可以排序实现，我这里使用Set来表示
        for(int i=index; i<nums.length; i++){
            if(set.contains(nums[i]))
                continue;
            set.add(nums[i]);
            swap(nums,i,index);
            dfs(nums,index+1);
            swap(nums,index,i);
        }
    }

    //从第一个数开始，不停的与他之后的进行交换，中间有DFS
    //这道题关键在于DFS 看具体的介绍
    public List<List<Integer>> permuteUnique2(int[] nums) {
        Arrays.sort(nums);
        if(nums.length==0)
            return list;
        dfs(nums,0);
        return list;
    }


    @Test
    public void test(){
        permuteUnique(new int[]{1,1,2,3});
    }
}
