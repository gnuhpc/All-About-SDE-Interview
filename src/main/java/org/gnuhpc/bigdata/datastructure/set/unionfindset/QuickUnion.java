package org.gnuhpc.bigdata.datastructure.set.unionfindset;

/*
lazy approach
Data structure.
Integer array  id[] of size  N .
Interpretation: id[i] is parent of  i .
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

//视频讲解:https://www.youtube.com/watch?v=YB3_c11GPEo
public class QuickUnion extends QuickUnionAbstract{

    public QuickUnion(int N) {
        super(N);
    }

    public int find(int i) {
        if (i != id[i]) {
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
        id[pid] = qid;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
