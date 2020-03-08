package org.gnuhpc.bigdata.leetcode.solutions;

import org.gnuhpc.bigdata.datastructure.unionfind.UnionFind;

/**
 * Copyright gnuhpc 2020/2/29
 */
public class ValidateBinaryTreeNodes1361 {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            // 若i节点的孩子节点之前已经有父亲了，则false
            // 若i节点和孩子节点已经在同一个连通域里了，说明形成了环，则false
            if (leftChild[i] != -1) {
                if (uf.find(leftChild[i]) != leftChild[i] || uf.isConnected(i, leftChild[i])) {
                    return false;
                }
                uf.union(i, leftChild[i]);
            }

            if (rightChild[i] != -1) {
                if (uf.find(rightChild[i]) != rightChild[i] || uf.isConnected(i, rightChild[i])) {
                    return false;
                }
                uf.union(i, rightChild[i]);
            }
        }
        // 最后判断一下连通域的数量
        return uf.count() == 1;
    }

    //考试用模板
/*
构造函数初始化数据结构需要 O(N) 的时间和空间复杂度；
连通两个节点union、判断两个节点的连通性connected、计算连通分量count所需的时间复杂度均为 O(1)。
 */
    public class UnionFind {
        // 连通分量个数
        private int   count;
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
            }
            else {
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
