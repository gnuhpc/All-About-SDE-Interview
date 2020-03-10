package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindRedundantDirectedConnection685 {
    /*
    思路：逆序假设删除该边后可以成为一棵树，则在删除该边后，若有以下两种情况，则该边不可删：
          情况一：删除该边后，存在两个树根节点（即入度为0的节点）
          情况二：删除该边后，存在一个树根节点，但从该节点遍历不到所有节点（即图被分成了两个互不连通的子图了）
    */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;

        //有向无权图构建和输入度记录
        List<Integer>[] graph = new ArrayList[n + 1];//构图
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        //由于是从1开始，因此开n+1的数组
        int[] in = new int[n + 1], out = new int[n + 1];//记录每个节点的出度与入度
        for (int[] nums : edges) {
            graph[nums[0]].add(nums[1]);
            out[nums[0]]++;
            in[nums[1]]++;
        }
        //为DFS做准备
        boolean[] visited = new boolean[n + 1];//记录每个节点的访问状态，初始为false，表示未访问（visited[0]无用）
        //从最后一条边开始
        for (int k = edges.length - 1; k >= 0; k--) {
            int i = edges[k][0], j = edges[k][1];
            int root = getRoot(in, out, i, j);//获取根节点  如果根节点个数大于一个 ，则返回-1  否则返回根节点
            if (root == -1) {//这条边不能删除
                continue;
            }
            //注意这里的包装类，因此你要删除的j不是idx而是数值
            graph[i].remove(new Integer(j)); //在 graph中 移除该边信息。如果移错了，后面应该补回来
            dfs(root, visited, graph); //深度遍历，之后查看visited除了visited[0]外是否都为true  若都为true，则表明应删除该边
            boolean tag = false;
            for (int m = 1; m < visited.length; m++) {
                if (!visited[m]) { //若有一个false，则代表删除该边以后有不连通的顶点了，因此不应该删除该边
                    tag = true;
                    break;
                }
            }
            if (tag) { //不应该删除该边，则应该把之前对这两个节点的度信息的“误操作”补偿回来，以及把边补回到图中
                out[i]++;
                in[j]++;
                graph[i].add(new Integer(j));
                Arrays.fill(visited, false);//一次dfs后，visited全部重置为false
            } else return new int[]{i, j};
        }
        return new int[]{0, 0};
    }

    //获得根节点  如果根节点数目不为1，则返回-1  否则返回根节点
    public int getRoot(int[] in, int[] out, int i, int j) {
        //先假设删掉这个边
        out[i]--;
        in[j]--;
        int k = 1, res = 1, rootCount = 0;
        while (k < in.length) {
            if (in[k] == 0) {
                res = k;
                rootCount++;
            }
            k++;
        }
        if (rootCount != 1) {
            //发现不止一个root节点，则恢复出入度
            out[i]++;
            in[j]++;
            return -1;
        } else return res;
    }

    // 深度优先遍历
    public void dfs(int i, boolean[] visited, List<Integer>[] graph) {
        visited[i] = true;
        for (int index : graph[i]) {
            if (!visited[index]) dfs(index, visited, graph);
        }
    }
}
