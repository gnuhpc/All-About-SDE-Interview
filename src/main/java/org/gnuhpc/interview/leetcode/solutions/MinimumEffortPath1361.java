package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class MinimumEffortPath1361 {
    //https://leetcode-cn.com/problems/path-with-minimum-effort/solution/duo-tu-xiang-xi-fen-xi-jie-ti-si-lu-fen-7z89x/
    public int minimumEffortPath(int[][] heights) {
        //初始化并查集
        int m = heights.length;
        int n = heights[0].length;
        UnionFind uf = new UnionFind(m*n);
        List<Edge> edges = new ArrayList<>();

        for(int i =0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                int index = uf.node(n,i,j);//二维转一维
                if(i>0){
                    edges.add(new Edge(index-n,index,Math.abs(heights[i][j] - heights[i - 1][j])));
                }
                if(j>0){
                    edges.add(new Edge(index-1,index,Math.abs(heights[i][j] - heights[i][j-1])));
                }
            }
        }
        //排序所有的边 从小到大。
        edges.sort((o1, o2) -> o1.val - o2.val);

        for(Edge edge:edges)
        {
            //合并后判断初始点和终点是否连通，如果连通则返回该边长
            uf.union(edge.x,edge.y);
            if(uf.isConnected(0,m*n-1)){
                return edge.val;
            }
        }
        return 0;


    }

    class Edge {
        int x;//from点
        int y;//to点
        int val;//边长
        public Edge(int x,int y,int val){
            this.x = x;
            this.y = y ;
            this. val = val;
        }
    }

    class UnionFind {
        // 连通分量个数
        private int count;
        // 存储一棵树
        private int[] id;
        // 记录树的“重量”
        //比如说size[3] = 5表示，以节点3为根的那棵树，总共有5个节点
        private int[] size;

        public UnionFind(int n) {
            this.count = n;
            id = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                size[i] = 1;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ)
                return;

            // 小树接到大树下面，较平衡
            if (size[rootP] > size[rootQ]) {
                id[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                id[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public boolean isConnected(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }

        public int find(int i) {
            if (i != id[i]) {
                id[i] = find(id[i]); // path compression
            }
            return id[i];
        }

        public int count() {
            return count;
        }

        //二维的时候计算ID使用, 这也是matrix 转化为一维的一个方法
        public int node(int cols, int i, int j) {
            return i * cols + j;
        }

        public void reBalance() {
            for (int i = 0; i < id.length; i++) {
                find(i);
            }
        }

        //返回哪个集合
        public int maxCount() {
            reBalance();

            int max_count = 0;

            for (int i : size) {
                max_count = Math.max(max_count, i);
            }

            return max_count;
        }
    }

}
