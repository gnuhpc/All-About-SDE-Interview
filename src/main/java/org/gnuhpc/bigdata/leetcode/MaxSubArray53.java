package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

//http://www.puzzlr.org/understanding-kadanes-algorithm-maximum-subarray-problem/
public class MaxSubArray53 {
    //Method1: 累加状态机
    public int maxSubArray(int[] nums) {
        int localMax = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            //Each number “decides” if it wants to use the previous subarray sum
            // or if it wants to start a new subarray from scratch.
            // 所以，这个if还有另一个写法 localMax = Math.max(num[i], localMax + num[i])
            if (localMax < 0) { //既然小于0了，那前边的都是欠债，扔了重新开始计数
                localMax = nums[i];
            } else {
                localMax += nums[i]; //如果不是小于0，则进行累加
            }
            if (localMax > globalMax) globalMax = localMax;
        }

        return globalMax;
    }


    //PrefixSum 方法 TODO 很通用的一个模板
    // The basic idea is to use pre-sum array
    // max =  Math.max(max, sum[i] - minSum).
    // (minSum is the minimum sum before A[i])
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0], minSum = Integer.MAX_VALUE;
        int sum[] = new int[nums.length+1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
            minSum = Math.min(0, Math.min(minSum, sum[i-1]));
            max = Math.max(max, sum[i] - minSum);
        }
        return max;
    }

    //Method 3: DP
    /*
     Apparently, this is a optimization problem, which can be usually solved by DP. So when it comes to DP, the first thing for us to figure out is the format of the sub problem(or the state of each sub problem).  The format of the sub problem can be helpful when we are trying to come up with the recursive relation.

At first, I think the sub problem should look like: `maxSubArray(int A[], int i, int j)`, which means the maxSubArray for A[i: j]. In this way, our goal is to figure out what `maxSubArray(A, 0, A.length - 1)` is. However, if we define the format of the sub problem in this way, it's hard to find the connection from the sub problem to the original problem(at least for me). In other words, I can't find a way to divided the original problem into the sub problems and use the solutions of the sub problems to somehow create the solution of the original one.
So I change the format of the sub problem into something like: `maxSubArray(int A[], int i)`, which means the maxSubArray for A[0:i ] which must has A[i] as the end element. Note that now the sub problem's format is less flexible and less powerful than the previous one because there's a limitation that A[i] should be contained in that sequence and we have to keep track of each solution of the sub problem to update the global optimal value. However, now the connect between the sub problem & the original one becomes clearer
     */
    public int maxSubArray3(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    //Method 4: 分治法
    public int maxSubArray4(int[] nums) {
        return subArray(nums, 0 ,nums.length -1 );
    }
    public int subArray(int[] A, int left, int right){
        if(left == right){return A[left];}
        int mid = left + (right - left) / 2;
        //可能的子数组区间可能出现在左边，也可能出现在右边，也有可能是横跨两边的
        int leftSum = subArray(A,left,mid);// left part
        int rightSum = subArray(A,mid+1,right);//right part
        int crossSum = crossSubarray(A,left,right);// cross part
        if(leftSum >= rightSum && leftSum >= crossSum){// left part is max
            return leftSum;
        }
        if(rightSum >= leftSum && rightSum >= crossSum){// right part is max
            return rightSum;
        }
        return crossSum; // cross part is max
    }
    public int crossSubarray(int[] A,int left,int right){
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;
        int mid = left + (right - left) / 2;
        //从中间向两边望去
        for(int i = mid; i >= left ; i--){
            sum = sum + A[i];
            if(leftSum < sum){
                leftSum = sum;
            }
        }
        sum = 0;
        for(int j = mid + 1; j <= right; j++){
            sum = sum + A[j];
            if(rightSum < sum){
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    @Test
    public void test() {
        int[] arr = {-1, 4, 2, -1};

        System.out.println(maxSubArray(arr));
    }
}
