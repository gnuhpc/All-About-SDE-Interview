package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright gnuhpc 2020/8/21
 */
public class TreeDiameter1245 {
    int res = 0; // 返回结果

    public int treeDiameter(int[][] edges) {
        // 利用边的信息构建出树的结构，即每个节点能和哪些节点相连接
        List<Integer>[] tree = new List[edges.length + 1];
        // 初始化
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        // 构建树
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
        dfs(tree, 0, -1); // 开始dfs
        return res;
    }

    // tree为树的结构图
// current为当前节点
// previous为前一个节点
// 返回值为：以当前节点为起点的一条最大路径长度
    int dfs(List[] tree, int current, int previous) {
        // 查看当前节点能与哪些节点连接
        List<Integer> list = tree[current];
        int max1 = 0; // 以当前节点为起点的一条最大路径长度
        int max2 = 0; // 以当前节点为起点的一条次大路径长度
        // 循环所有与当前节点相连的点
        for (int next : list) {
            // 防止走回头路
            if (next != previous) {
                // dfs得到下一个节点一条路径的最大长度
                // 加一之后为当前节点一条路径的长度
                int max = dfs(tree, next, current) + 1;
                // 比较当路径长度与之前得到的max1，max2，并更新最大值
                if (max >= max1) {
                    max2 = max1;
                    max1 = max;
                } else if (max >= max2) {
                    max2 = max;
                }
                // max1+max2得到当前节点最大边长,与返回结果比较，更新最大值
                res = Math.max(res, max1 + max2);
            }
        }
        // 返回当前节点一条路径的最大长度
        return max1;
    }
}
