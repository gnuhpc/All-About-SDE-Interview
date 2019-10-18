package org.gnuhpc.bigdata.datastructure.unionfind;

/*
lazy approach
Data structure.
Integer array  id[] of size  N .
Interpretation: id[i] is cor of  i .
Root of  i is id[id[id[...id[i]...]]] .

Find. Check if p and q have the same find.
Union. Set the id of q's find to the id of p's find.

Quick-union defect.
    Trees can get tall.
    Find too expensive (could be N steps)
    Need to do find to do union

Quick Union is Faster than Quick Find ,一般都采用这种
 */
// 并查集四要素：
/*
1. 初始化id数组，自己parent指向自己
2. find， 找不到就父亲指向查找父亲
3. union， 查p 查q， p的parent设置为q
4. connect， 返回查p是不是等于查q

 */

import java.util.HashMap;
import java.util.Map;

//视频讲解:https://www.youtube.com/watch?v=YB3_c11GPEo
public class QuickUnion extends QuickUnionAbstract{
    int count = 0;

    public QuickUnion(int N) {
        super(N);
        this.count = N;
    }

    public int find(int i) { //path compression
        if (i != id[i]) { //不等于他就递归的赋值进去
            // path compression , 小技巧
            id[i] = find(id[i]);
        }
        return id[i];
    }

    //O(N) worst
    // find of p is find of q; p belongs to q's connected component group
    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if (pid == qid) return; //注意如果已经connected就不做了，因为count要--，这个不是幂等性操作
        id[pid] = qid;
        count--;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void reBalance() {
        for (int i = 0; i < id.length; i++) {
            find(i);
        }
    }

    public int maxCount() {
        reBalance();

        Map<Integer, Integer> hp = new HashMap<>();
        for (int i = 0; i < id.length; i++) {
            int key = id[i];
            if (hp.containsKey(key))
                hp.put(key, hp.get(key) + 1);
            else
                hp.put(key, 1);
        }

        // find max frequency.
        int max_count = 0;

        for (int i : hp.values()) {
            max_count = Math.max(max_count, i);
        }

        return max_count;
    }

    public int count() {
        return count;
    }

    //二维的时候计算ID使用 TODO 这也是matrix 转化为一维的一个方法
    public int node(int cols, int i, int j) {
        return i * cols + j;
    }

}
