package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;

public class FindRedundantDirectedConnection685 {
    /*
了解题目意思后，我们要面对的是两种问题。

图形局部有环
某个节点有两个爹
对于情况1，我是暴力解决的，就是从最后一条边开始删除，若删除后符合要求，则返回这条边。由于我之前保存过任意一个点的孩子节点，所以若某个点无孩子，那么肯定不删除这条边，直接删除下一条边。
对于情况2，那么待删除的是两条边的其中之一，因此我们删除后一条边，若删除后，符合要求，就返回这条边。

在判断删除后是否符合要求时，记得要保存遍历的节点数量，仅当成功遍历的节点数量等于N时，才算是符合要求。
假若你删除的恰好是局部环外的某条边，queue最后也会变成空，但此时显然不符合题意，
所以要加上遍历的节点的数量，这个和你判断是否不为环的逻辑可能有差异。

作者：ccdmw
链接：https://leetcode-cn.com/problems/redundant-connection-ii/solution/bao-li-jie-jue-by-ccdmw/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

    private int N;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        //此题存在两种情况
        //1.图形局部有环
        //2.某个节点有两个爹 即入度为2
        int target = 0;
        int root = 0;
        N = edges.length;
        boolean flag = false;
        int[] inDegree = new int[N+1];
        List<Integer>[] lists = new List[N+1];
        for(int i=1;i<=N;i++){
            lists[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
            lists[edge[0]].add(edge[1]);
        }
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 2){
                target = i;
                flag = true;
            }
            else if (inDegree[i] == 0)
                root = i;
        }
        //有一个节点有两个爹
        //删除其中一条边即可保证图变为树
        if (flag){
            int[][] res = new int[2][2];
            int index = 0;
            for (int[] edge : edges) {
                if (edge[1] == target){
                    res[index][0] = edge[0];
                    res[index][1] = edge[1];
                    index++;
                }
            }
            //先删除后面的一条边
            //入度减一
            //从list中移除
            inDegree[res[1][1]]--;
            lists[res[1][0]].remove(new Integer(res[1][1]));
            if (deleteEdge(root,inDegree,lists))
                return res[1];
            return res[0];
        }
        //局部有环
        else {
            for (int i = edges.length - 1; i >= 0; i--) {
                if (lists[edges[i][1]].size() != 0){
                    inDegree[edges[i][1]]--;
                    lists[edges[i][0]].remove(new Integer(edges[i][1]));
                    if (deleteEdge(edges[i][1],inDegree,lists))
                        return edges[i];
                }
            }
        }
        return null;
    }

    private boolean deleteEdge(int root,int[] inDegree,List<Integer>[] lists){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        int temp = 1;
        while (!queue.isEmpty()){
            Integer in = queue.poll();
            List<Integer> list = lists[in];
            for (Integer i : list) {
                inDegree[i]--;
                if (inDegree[i] == 0){
                    queue.add(i);
                    temp++;
                }
                else
                    return false;
            }
        }
        if (temp == N)
            return true;
        return false;
    }

}
