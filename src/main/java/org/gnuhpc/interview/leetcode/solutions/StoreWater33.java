package org.gnuhpc.interview.leetcode.solutions;

public class StoreWater33 {
    public int storeWater(int[] bucket, int[] vat) {
        int max = 0;
        for(int v:vat)
            if(max < v) max = v;
        if(max == 0) return 0;
        int n = bucket.length;
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i <= 10000; i++) {//遍历倒水次数
            int per = 0;
            int cur = i;//倒水i次，所以操作次数+i
            for(int j = 0; j < n; j++) {//遍历每个水缸
                per = (vat[j] + i - 1) / i;// 水槽容量/倒水次数=每次倒水量
//+（i - 1）目的是为了向上取整(除完后如果有余数，加上i-1之后就一定会多商1，从而达到向上取整的功能)
//使用vat[j]%i==0 ? vat[j]/i : vat[j]/i+1 代替也行，但是更慢
                cur += Math.max(0, per - bucket[j]);// 每次倒水量-初始水量=需要升级次数
            }
            ans = Math.min(ans, cur);//所有倒水次数中，取最小的操作次数
        }
        return ans;
    }
}
