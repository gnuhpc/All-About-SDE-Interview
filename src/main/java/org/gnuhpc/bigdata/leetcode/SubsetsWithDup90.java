package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
思路同47
 */
public class SubsetsWithDup90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }
    private void dfs(int[] nums, int start, List<Integer> temp,List<List<Integer>> res){
        res.add(new ArrayList<>(temp));
        if(start == nums.length){
            return;
        }

        Set<Integer> visited = new HashSet<>();
        for(int i = start; i < nums.length; i++){
            if(visited.add(nums[i])){
                temp.add(nums[i]);
                dfs(nums, i + 1, temp,res);
                temp.remove(temp.size() - 1);
            }
        }
    }

    /*

    思路2： 标记法, 取还是不取的逻辑大战
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        robot(nums, 0, new boolean[nums.length], res);
        return res;
    }

    private void robot(int[] nums, int start, boolean[] visited, List<List<Integer>> res) {
        if (start >= nums.length) {
            res.add(IntStream.range(0,visited.length).filter(i->visited[i]).mapToObj(i-> nums[i]).collect(Collectors.toList()));
            return;
        }

        //规则是能取前边的就取前边的
        //如果和前边的数字相同，且前边没有取，则后边也不取
        if (start > 0 && nums[start-1] == nums[start] && !visited[start - 1]){
            robot(nums,start+1,visited,res);

        } else { //前边取了，则后边这个可以取也可以不取
            visited[start] = true;
            robot(nums, start+1, visited, res);

            visited[start] = false;
            robot(nums, start+1, visited, res);
        }
    }

    @Test
    public void test(){
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
        System.out.println(subsetsWithDup2(new int[]{1,2,2}));
    }

    // add by tina， 与方法一类似,注意与78比较
    public List<List<Integer>> subsetsWithDup5(int[] nums) {
        if(nums == null) return null;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        for(int k = 0; k<=nums.length;k++){
            helper(nums,k,0,temp,res);
        }
        System.out.println(new ArrayList<>());
        return res;

    }

    public void helper(int[] nums, int k, int start, List<Integer> temp,List<List<Integer>> res){
        if(temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i< nums.length; i++){ //<nums.length -(k-temp.size())+1
            //System.out.println("i="+i+", temp = "+ temp+",k = " +k);
            if(i>start && nums[i] == nums[i-1]) continue;
            temp.add(nums[i]);
            helper(nums,k,i+1,temp,res);
            temp.remove(temp.size()-1);
        }

    }
}
