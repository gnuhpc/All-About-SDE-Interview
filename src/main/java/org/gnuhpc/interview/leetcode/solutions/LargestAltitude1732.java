package org.gnuhpc.interview.leetcode.solutions;

public class LargestAltitude1732 {
    public int largestAltitude(int[] gain) {
        int level=0,maxlevel=0;
        for(int distance:gain){
            level+=distance;
            maxlevel=Math.max(maxlevel,level);
        }
        return maxlevel;
    }
}
