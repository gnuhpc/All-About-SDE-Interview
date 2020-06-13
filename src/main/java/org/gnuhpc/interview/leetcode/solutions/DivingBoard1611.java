package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.Arrays;

public class DivingBoard1611 {

    public int[] divingBoard(int shorter, int longer, int k) {
        if(k == 0) return new int[0];
        if(k == 1) return new int[]{shorter, longer};
        if(shorter == longer) return new int[]{k*shorter};
        int[] ans = new int[k+1];
        for(int i=0; i<=k; i++){
            ans[i] = shorter*i + longer*(k-i);
        }
        Arrays.sort(ans);
        return ans;
    }

    @Test
    public void test(){
        divingBoard(1,2,3);
    }
}
