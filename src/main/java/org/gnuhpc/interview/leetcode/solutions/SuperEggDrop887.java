package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/4/11
 * https://youtu.be/hJa6LhaZHcs
 */
public class SuperEggDrop887 {
    Integer[][] memo = null;

    public int superEggDrop(int K, int N) {
        memo = new Integer[K + 1][N + 1];
        return dp(K, N);
    }

    /**
     * 当前K个鸡蛋共有N层楼状态时 返回这个状态下确定 F的值的最小移动次数
     * 也就是求最坏情况的最小值
     */
    private int dp(int k, int n) {
        //base case 层数N等于0时不需要扔鸡蛋,当鸡蛋数K为1时，只能每层逐个尝试剩下所有楼层
        if (k == 1) return n;
        if (n <= 0) return 0;

        //添加一个备忘录 消除之前算过的重叠子问题
        if (memo[k][n] != null) return memo[k][n];

        int tmp = Integer.MAX_VALUE;

        // //第一种 从第1层楼到第n层楼每层楼开始逐个尝试作为切入点 (会超时)
        // Refer to: https://youtu.be/KVfxgpI3Tv0
        // for(int i=1;i<=n+1;i++){
        //     //当选择在第i层楼扔了鸡蛋之后 可能出现鸡蛋碎了和鸡蛋没碎两种情况：
        //     //当鸡蛋碎了 问题状态转嫁为求k-1个鸡蛋 搜索的楼层区间变为1~i-1共i-1层楼时求解
        //     int eggBreak=dp(k-1,i-1);
        //     //当鸡蛋没碎 问题状态转嫁为鸡蛋的个数K不变 搜索楼层区间变为i+1~N共N-i层楼时求解
        //     int eggUnBreak=dp(k,n-i);
        //     //最终以i层为切入点求解的答案 为两种状态的最坏情况 并加上i层时操作1 并更新最小值
        //     tmp = Math.min(tmp,Math.max(eggBreak,eggUnBreak)+1);
        // }

        //第二种 利用二分查找的方式直接找到对应点(AC通过)
        //第一种线性逐个尝试切入点 然后求每个切入点两种状态的较大值 再求这些最大值之中的最小值 其实就是求这两条单调递增和单调递减直线的交点 相当于求上半部V形山谷值 二分查找来快速寻找这个点
        int lo = 1, hi = n;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int eggBreak = dp(k - 1, mid - 1);
            int eggUnBreak = dp(k, n - mid);
            if (eggBreak > eggUnBreak) {
                hi = mid - 1;
                tmp = Math.min(tmp, eggBreak + 1);
            } else {
                lo = mid + 1;
                tmp = Math.min(tmp, eggUnBreak + 1);
            }
        }

        //添加到备忘录里
        memo[k][n] = tmp;
        //返回当前k个鸡蛋n层楼时求解的子问题的结果
        return tmp;
    }

    //链接：https://leetcode-cn.com/problems/super-egg-drop/solution/la-bu-la-duo-xiao-hong-mao-da-niu-ti-jie-dai-ma-ja/
}
