package org.gnuhpc.bigdata.datastructure.unionfind;

import java.util.HashMap;
import java.util.Map;

//考试用模板
public class UnionFind {
    int[] id, size;
    int count;

    public UnionFind(int len) {
        this.id = new int[len];
        this.size = new int[len];
        this.count = len;
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pid = find(p), qid = find(q);
        if (pid == qid) return; //注意如果已经connected就不做了.因为count要--，这个不是幂等性操作
        if (this.size[pid] < this.size[qid]) {
            this.id[pid] = qid;
            this.size[qid] += this.size[pid];
        } else {
            this.id[qid] = pid;
            this.size[pid] += this.size[qid];
        }
        this.count--;
    }

    public int find(int i) {
        if(i != id[i]) {
            id[i] = find(id[i]); // path compression
        }
        return id[i];
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
