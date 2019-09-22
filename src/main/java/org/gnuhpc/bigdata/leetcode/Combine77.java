package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Combine77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        dfs(1, n, k, res, new ArrayList<>());
        return res;
    }

    /*
    TODO 组合DFS套路：
    1. 原数据集
    2. 从哪里开始（因为无序不要重复，但是还要剔除已经用过的，因此要一直往前找，这个就是从哪里开始往前找的依据）
    3. 临时结果集
    4. 最终结果集

    函数内的处理逻辑：
    1. 判断退出条件，处理临时结果加入到最终结果
    2. 遍历每个数字，加入临时结果集,然后递归进入，除了start向后一个（因为已经选择过了）,其余传入的内容一样
    3. 然后回溯，从临时结果集中去掉最后的那个元素
     */

    private void dfs(int start, int end, int size, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == size) res.add(new ArrayList<>(temp));
        for (int i = start; i <= end ; i++ ){
            temp.add(i);
            dfs(i + 1, end, size, res, temp);
            temp.remove(temp.size()-1);
        }
    }

    @Test
    public void test() {
        System.out.println(combine(4, 2));
    }


    // add by @Tina
    // 注意这类题目中，dfs不用返回值，
    // 因为List是引用类型，从定义开始，到传参进去
    // 任何一次改变，都会被带回来
    // 注意回溯的过程，需要把刚刚这一轮加到temp中的元素remomve，
    // 继续遍历下一个
    // i=n时，已经没有元素可以加进list了，不必再进行递归，可进行剪枝
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        dfs2(res, temp, n, k, 1);
        return res;
    }

    private void dfs2(List<List<Integer>> res, List<Integer> temp, int n, int k, int m) {
        if (temp.size() == k) {
            res.add(new ArrayList<Integer>(temp));
            return;
        }
        /*or(int i=m; i<=n; i++) {
            temp.add(i);
            dfs(res, temp, n, k, i+1);
            temp.remove(temp.size()-1);
        }*/

        // 此时temp还剩下k-temp.size个位置，确保[i,...,n]中至少包含k-temp.size个元素
        // i <= n-(k-temp.size) +1
        for (int i = m; i <= n - (k - temp.size()) + 1; i++) {
            temp.add(i);
            dfs2(res, temp, n, k, i + 1);
            temp.remove(temp.size() - 1);
        }
    }


}
