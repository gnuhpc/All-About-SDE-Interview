package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//组合类的
/*
问题模型：求出所有满足条件的“组合”。
判断条件：组合中的元素是顺序无关的。
时间复杂度：与 2^n 相关。
 */
public class Subsets78 {
    /*
    分别求长度为0到n的子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i <= nums.length; i++) {
            dfs(nums, 0, i, new ArrayList<>(), res);
        }

        return res;
    }

    private void dfs(int[] nums, int start, int size, ArrayList<Integer> temp, List<List<Integer>> res) {
        if (temp.size() == size) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);  //取
            dfs(nums, i + 1, size, temp, res);
            temp.remove(temp.size() - 1);//backtracking 不取
        }
    }

    /*
    思路2，和池子里的已经有的元素不断的进行并集
    []   ->  [b]      -> [b,c]
    [a]      [a,b]    -> [a,b,c]
                        ->[a,c]
                        ->[c]

     */
    public List<List<Integer>> subsets2(int[] nums) {
        if (nums.length == 0) {
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        res.add(temp);

        //i为要遍历的原数据集的元素下标
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < res.size(); j++) {
                temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
            }
        }
        return res;
    }

    /*
    Method3 : dfs 方法一和方法二的集合
     */

    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, new ArrayList<>(), 0);
        return res;
    }

    public void dfs(int[] nums, List<List<Integer>> res, List<Integer> temp, int start) {

        res.add(new ArrayList<>(temp));
        if (start == nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(nums, res, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    /*
    Method 4 : Robot ，TODO 重要方法
     */

    public static boolean[] v = new boolean[100];
    public static List<List<Integer>> ans = new ArrayList<>();

    public void robot(int idx, int[] nums) {
        if (idx >= nums.length) {
            List<Integer> r = new ArrayList<>();
            for (int i = 0; i < nums.length; ++i) {
                if (v[i]) {
                    r.add(nums[i]);
                }
            }
            ans.add(r);
            return;
        }
        v[idx] = true;
        robot(idx + 1, nums); //yes
        v[idx] = false;
        robot(idx + 1, nums); //no
    }

    public List<List<Integer>> subsets4(int[] nums) {
        ans.clear();
        robot(0, nums);
        return ans;
    }

    @Test
    public void test() {
        System.out.println(subsets4(new int[]{1, 2, 3}));
//        System.out.println(subsets2(new int[]{1,2,3}));
    }
}
