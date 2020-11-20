package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

public class CanCompleteCircuit134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int ans = 0;
        int cost1 = 0;//当前花费
        int canComplete = 0;//记录能否完成一周
        for(int i = 0; i < gas.length; i++){
            int costInner = gas[i] - cost[i];
            canComplete += costInner;
            cost1 += costInner;
            if(cost1 < 0){
                cost1 = 0;
                ans = i + 1;//如果当前加油站花费比实际汽油多则需要从下一个加油站出发
            }
        }
        if(canComplete >= 0){//如果总花费不超过总汽油资源则能完成一圈，ans也满足条件
            return ans;
        }
        return -1;//否则不能完成一圈
    }


    @Test
    public void test() {
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
        System.out.println(canCompleteCircuit(new int[]{5, 1, 2, 3, 4}, new int[]{4, 4, 1, 5, 1}));
    }
}
