package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.unionfind.UnionFind;

import java.util.Arrays;
import java.util.Comparator;

public class MaxNumEdgesToRemove1579 {
     /*   优先使用类型3进行连通

先排序，将类型3排在最前面
分别为类型1和类型2创建并查集
然后遍历：
----如果不连通且是类型3，在两个并查集上连通两边；
----如果不连通是类型2，则在第2个并查集上连通两点；
----如果不连通是类型1，则在第1个并查集上连通两点；
----如果已经连通，结果加一；
输出结果：判断两个并查集是否可完全遍历
----如果可以，输出结果；
----否则，输出-1；

作者：hello-angel
链接：https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/solution/javacha-ji-si-lu-fen-xiang-xiang-xi-zhu-ltud6/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        //排序（由大到小）
        Arrays.sort(edges, (o1, o2) -> o2[0] - o1[0]);
        int len = edges.length;
        int ans = 0;
        //实例化两个并查集
        UnionFind uf1 = new UnionFind(n);
        UnionFind uf2 = new UnionFind(n);
        //遍历
        for(int[] edge:edges){
            if(edge[0]==3){
                if(uf1.find(edge[1]-1)==uf1.find(edge[2]-1)){
                    ans++;
                }else{
                    uf1.union(edge[1]-1,edge[2]-1);
                    uf2.union(edge[1]-1,edge[2]-1);
                }
            }else if(edge[0]==2){
                if(uf2.find(edge[1]-1)==uf2.find(edge[2]-1)){
                    ans++;
                }else{
                    uf2.union(edge[1]-1,edge[2]-1);
                }
            }else if(edge[0]==1){
                if(uf1.find(edge[1]-1)==uf1.find(edge[2]-1)){
                    ans++;
                }else{
                    uf1.union(edge[1]-1,edge[2]-1);
                }
            }
        }
        //输出结果
        if(uf1.count() == 1 && uf2.count() == 1){
            return ans;
        }
        return -1;
    }
}
