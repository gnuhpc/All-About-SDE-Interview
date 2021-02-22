package org.gnuhpc.interview.leetcode.solutions;

public class FindJudge997 {
    public int findJudge(int N, int[][] trust) {
        if(N==1) {
            if(trust.length == 1) return -1;
            else return 1;
        }
        int[] indegrees = new int[N];
        int[] outdegrees = new int[N];
        for(int[] t: trust){
            indegrees[t[1]-1]++;
            outdegrees[t[0]-1]++;
        }

        for (int i = 0; i < N; i++) {
            if(indegrees[i]==N-1 && outdegrees[i]==0) return i+1;
        }
        return -1;
    }
}
