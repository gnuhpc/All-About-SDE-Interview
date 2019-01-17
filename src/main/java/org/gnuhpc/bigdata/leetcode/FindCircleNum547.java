package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class FindCircleNum547 {
    /*
    Method 1: dfs
     */
    public int findCircleNum(int[][] M) {
        int circleNum = 0;
        boolean[] hasVisited = new boolean[M.length];
        for (int i = 0; i < M.length; i++) {
            if (!hasVisited[i]) {
                dfs(M, i, hasVisited);
                circleNum++;
            }
        }
        return circleNum;
    }

    private void dfs(int[][] M, int i, boolean[] hasVisited) {
        hasVisited[i] = true;
        for (int j = 0; j < M[0].length; j++) {
            if (M[i][j] == 1 && !hasVisited[j]) {
                dfs(M, j, hasVisited);
            }
        }
    }

    /*
    Method 2: dfs Iterative
     */
    public int findCircleNum2(int[][] M) {
        boolean[] visited = new boolean[M.length];// array to keep track of visited in dfs
        Deque<Integer> stack = new LinkedList<>();// stack to execute dfs

        int res = 0;  // final ans stored in circles

        for(int i = 0; i < M.length; i++){
            if(!visited[i]){
                res++;  // // person i unvisited so start new friend circle

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

        return res;
    }

    /*
    Method3 : Union find
     */
    public int findCircleNum3(int[][] M) {
        int count = M.length;
        int[] root = new int[M.length];

        // 构建并差集
        for(int i=0;i<M.length;i++){
            root[i] =i;
        }
        for(int i=0;i<M.length;i++){
            for(int j=0;j<M[0].length;j++){
                if(M[i][j]==1){
                    int rooti = root(root,i);
                    int rootj = root(root,j);
                    //合并，表示相连
                    if(rooti!=rootj){
                        root[rooti] = rootj;
                        count--;
                    }
                }
            }
        }
        return count;
    }

    public int root(int[] arr, int id){
        while(arr[id]!=id){
            arr[id] = arr[arr[id]];
            id = arr[id];
        }
        return id;
    }


    @Test
    public void test(){
        System.out.println(findCircleNum(new int[][]{
                {1,1,0},
                {1,1,0},
                {0,0,1}
        }));
    }
}
