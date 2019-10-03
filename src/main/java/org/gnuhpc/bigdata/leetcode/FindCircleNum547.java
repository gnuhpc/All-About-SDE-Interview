package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.datastructure.unionfind.QuickUnion;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class FindCircleNum547 {
    /*
    Method 1: dfs  //TODO DFS套路
    Time: O(N^2) Space:O(N)
     */
    public int findCircleNum(int[][] M) {
        if(M == null || M.length == 0 || M[0] == null || M[0].length == 0) return 0;
        int N = M.length;
        int circle = 0;
        // Step1: visited 标注节点是不是被访问过
        boolean[] visited = new boolean[N];
        // Step2: 逐个节点进行，没有访问过的就dfs进去
        for(int i = 0; i < N; i++){
            if(!visited[i]) {
                visited[i] = true;
                circle++; //没有被前边的dfs访问过的就是新的一个circle 的出现
                dfs(M, visited, i);
            }
        }
        return circle;
    }
    public void dfs(int[][] M, boolean[] visited, int id){
        for(int i = 0; i < M.length; i++){
            // Step3: 把这个节点相关朋友联系的，且没有被访问过的统统标注为visited
            if(!visited[i] && i!=id && M[id][i] == 1){
                visited[i] = true;
                dfs(M, visited, i);
            }
        }
    }

    /*
    Method 2: dfs Iterative
     */
    public int findCircleNum2(int[][] M) {
        boolean[] visited = new boolean[M.length];// array to keep track of visited in dfs
        Deque<Integer> stack = new LinkedList<>();// stack to execute dfs

        int circle = 0;  // final ans stored in circles

        for(int i = 0; i < M.length; i++){
            if(!visited[i]){
                circle++;  // // person i unvisited so start new friend circle

                // dfs magic : {
                // push to stack -
                // pop top -
                // retrieve neighbours -
                // repeat first 3 steps for each unvisited neighbour
                // until stack empty }
                stack.push(i);

                while(!stack.isEmpty()){
                    int current = stack.pop();
                    visited[current] = true;        // mark node visited

                    // retrieve it's unvisited neighbours and push them to stack
                    for(int j = 0; j < M[current].length; j++){
                        if(!visited[j] && M[current][j] == 1){
                            stack.push(j);
                        }
                    }
                }
            }
        }

        return circle;
    }

    /*
    Method3 : Union find
     */
    public int findCircleNum3(int[][] M) {
        //count 初始化为最多N个圈子，自己都和自己一个圈子，不和别人在一个圈子
        int count = M.length;

        // 构建并差集
        //一个表示N个同学朋友关系的图有N个节点。
        // 由于我们知道每个人都是自己的朋友，
        // 因此我们在初始化时，这个图有N个子图，每个子图都只包含一个节点。

        QuickUnion qu = new QuickUnion(count);

        for(int i=0;i<M.length;i++){
            for(int j=0;j<M[0].length;j++){
                //当M[i][j]=1时，同学i和同学j是直接朋友，因此他们一定在一个朋友圈里。
                if(M[i][j]==1){
                    //判断i和j是不是在并查集中已经相连
                    if(!qu.connected(i,j)){
                        //如果没有则合并
                        qu.union(i,j);
                        count--;
                    }
                }
            }
        }
        return count;
    }


    /*
    Method4: BFS
     */

    //BFS: time:O(N^2) N: the number of students; space:O(N^2)
    //use an (boolean) array to check whether a student has been added to one circle.
    //we traverse each student from 0 to N - 1. When we find a student that hasn't been added to one circle, we assume it is a new one and use BFS to add his direct or indirect friend to this new circle.
    public int findCircleNum4(int[][] M) {
        if(M == null || M.length == 0 || M[0] == null || M[0].length == 0) return 0;
        int N = M.length;
        int circle = 0;
        boolean[] visited = new boolean[N];
        for(int i = 0; i < N; i++){
            if(visited[i]) continue;
            circle++;
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            visited[i] = true;
            while(!q.isEmpty()){
                int person = q.poll();
                for(int j = 0; j < N; j++){
                    if(!visited[j] && M[person][j] == 1){
                        visited[j] = true;
                        q.add(j);
                    }
                }
            }
        }
        return circle;
    }



    @Test
    public void test(){
        assertEquals(2,findCircleNum(new int[][]{
                {1,1,0},
                {1,1,0},
                {0,0,1}
        }));
    }
}
