package org.gnuhpc.bigdata.leetcode;

public class BestTimetoBuyandSellStock121 {
    public int maxProfit(int[] prices) {
        int res = 0;
        int minValue = Integer.MAX_VALUE;
        int n = prices.length;
        for(int i = 0; i < n; i++){
            minValue = Math.min(minValue,prices[i]);
            res = Math.max(res,prices[i]-minValue);
        }
        return res;
    }

}
