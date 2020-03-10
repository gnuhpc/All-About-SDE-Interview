package org.gnuhpc.interview.leetcode.solutions;

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
    DFS
     */
    // add by @Tina
    // 注意这类题目中，dfs不用返回值，
    // 因为List是引用类型，从定义开始，到传参进去
    // 任何一次改变，都会被带回来
    // 注意回溯的过程，需要把刚刚这一轮加到temp中的元素remomve，
    // 继续遍历下一个
    // i=n时，已经没有元素可以加进list了，不必再进行递归，可进行剪枝

    private void dfs(int start, int end, int size, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == size) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= end; i++) {
            temp.add(i);
            dfs(i + 1, end, size, res, temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test() {
        System.out.println(combine(4, 2));
    }

    /*
    Robot方法
     */
    public List<List<Integer>> combine2(int n, int k) {
        int[] sets = new int[n];
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            sets[i - 1] = i;
        }
        robot(k, 0, new ArrayList<>(), sets, res);
        return res;
    }

    private void robot(int k, int start, List<Integer> tmp,
                       int[] sets, List<List<Integer>> res) {
        if (start == sets.length || k == 0) {
            if (k == 0) { //如果只是start到了终点，但是没有选出足够多的数值，这个结果我们也不要
                res.add(new ArrayList<>(tmp));
            }
            //虽然不要，也不能再往下走，因为start已经越界
            return;
        }


        tmp.add(sets[start]);
        robot(k - 1, start + 1, tmp, sets, res);

        tmp.remove(tmp.size() - 1);
        robot(k, start + 1, tmp, sets, res);
    }

    /*
  Robot方法 (标记计数)
   */
    boolean[] selected;
    List<List<Integer>> res;
    int[] sets;

    public List<List<Integer>> combine3(int n, int k) {
        selected = new boolean[n];
        sets = new int[n];
        res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            sets[i - 1] = i;
        }
        robot(k, 0);
        return res;
    }

    private void robot(int k, int start) {
        if (start == selected.length || k == 0) {
            if (k == 0) { //如果只是start到了重点，但是没有选出足够多的数值，这个结果我们也不要
                List<Integer> tmp = new ArrayList<>();
                for (int i = 0; i < selected.length; i++) {
                    if (selected[i]) {
                        tmp.add(sets[i]);
                    }
                }
                res.add(tmp);
            }
            //虽然不要，也不能再往下走，因为start已经越界
            return;
        }

        selected[start] = true;
        robot(k - 1, start + 1);

        selected[start] = false;
        robot(k, start + 1);
    }

}
