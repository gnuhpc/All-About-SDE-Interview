package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.*;

//The basic idea of this solution is a BSF search for shortest path, take 12 as an example, as shown below, the shortest path is 12-8-4-0:
// 12->8(12-4)->4(8-4)->0(4-4)
/*
http://1.bp.blogspot.com/-gTo4JCOZp3s/VfCn4dnenqI/AAAAAAAAAP8/ro_1LiL1yWM/s1600/pefect_square_bfs.jpg
基本模型，仍是 number1 = square + number2
即，number1, number2当作节点，而他们之间的边，则是一个 平方。
即一个数，加上一个 平方， 从而得到另一个数。
从根结点整数0从发，进行广度优先搜索，直到节点n为止。
即，
 节点0加上一系列平方，得到一下层次的各个结点。
而对下一层次的结点，重复进行上面的步骤。

此处的visited数组很重要，否则会出现内存不足。
因为实际上这是一个图，而不是单纯的树。

In this problem, we define a graph where each number from 0 to n is a node. Two numbers p < q is connected if (q-p) is a perfect square.
So we can simply do a Breadth First Search from the node 0.

if you want to find “….’s least number to do something”, you can try to think about solving it in bfs way.

Don’t forget to use a set visited to record which nodes have been visited.
Sample node can be reached through multiple ways
but bfs always makes sure whenever it is reached with the least steps, it is flagged as visited.
 */
public class NumSquares279 {
    int numSquares(int n) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(0);
        visited.add(0);
        int depth = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            depth++;
            while(size-- > 0) {
                int u = q.poll();
                for(int i = 1; i*i <= n; i++) {
                    int v = u+i*i;
                    if(v == n) {
                        return depth;
                    }
                    //这一层就大于n，后边越加越大，因此除去这个分支
                    if(v > n) {
                        break;
                    }
                    if(!visited.contains(v)) {
                        q.offer(v);
                        visited.add(v);
                    }
                }
            }
        }
        return depth;
    }


    /*
    模型同上，进行深度优先搜索。
从n从发，减去一个平方，即得到下一层的一个节点。 直到找到节点0为止。
遍历所有路径，记录下最短的路径数。
1. 遍历时，使用变量level，限制递归的深度。以节省时间。
2. 先减去较大的平方，再减去较小的平方。 这样，当搜索到节点0时，所用的步数（即深度），不会特别大。从而用此深度，限制后续的搜索深度。
因为我们要找的是最短路径。
反之如果从1个平方开始递减的话，会出现大量的无用搜索。比如每次只减掉1，搜索n，将第一次会达到n层。
     */


    public int numSquares2(int n) {
        return dfs(n,0,n);
    }
    public int dfs(int n, int level, int min) {
        if(n == 0 || level >= min)
            return level;
        for(int i = (int) Math.sqrt(n); i > 0; i--) {
            min = dfs(n - i*i, level+1, min);
        }
        return min;
    }

    int minimum;
    int numSquares3(int n) {
        int digit = (int)Math.sqrt(n);
        if(digit*digit == n) return 1;

        minimum = n;//this is the maximum number
        dfs(n, 0, digit, 0);
        return minimum;
    }
    void dfs(int n, int curSum, int curDigit, int curNum)
    {
        if(curSum > n || curNum >= minimum) return;
        if(curSum == n)
        {
            minimum = Math.min(curNum,minimum);
            return;
        }
        if(curDigit <= 0) return;
        if(curDigit == 1)
        {
            curNum += n-curSum;
            minimum = Math.min(curNum,minimum);
            return;
        }
        //either use curDigit or not use
        if(n-curSum >= curDigit*curDigit)
            dfs(n, curSum+curDigit*curDigit, curDigit, curNum+1);
        dfs(n, curSum, curDigit-1, curNum);
    }

    @Test
    public void test(){
        numSquares2(12);
    }


}
