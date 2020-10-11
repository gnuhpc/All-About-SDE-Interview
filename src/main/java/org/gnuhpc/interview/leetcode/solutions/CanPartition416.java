package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Utils;
import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 19-7-17
 */
public class CanPartition416 {

    //DFS
       /*
    执行用时：4 ms, 在所有 Java 提交中击败了93.11%的用户
    内存消耗：38.5 MB, 在所有 Java 提交中击败了58.28%的用户

    invalidSet为无法凑出的目标集合
    */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        Set<Integer> invalidSet = new HashSet<>();
        //boolean res =  helper(nums, 0, sum/2,  invalidSet);
        //System.out.println(invalidSet);
        //return res;
        return helper(nums, 0, sum / 2, invalidSet);
    }

    public boolean helper(int[] nums, int idx, int target, Set<Integer> invalidSet) {
        if (target < 0 || invalidSet.contains(target)) {
            return false;
        }

        if (target == 0) {
            return true;
        }


        //TODO 给定一个数组求任何一个子序列：
        /*
         helper(int[] nums, int idx){
            for(int i = idx;i<nums.length;i++){
                (helper(nums, i+1);
            }
        }
         }
         */
        for (int i = idx; i < nums.length; i++) {
            if (helper(nums, i + 1, target - nums[i], invalidSet)) return true;
        }
        invalidSet.add(target);
        return false;
    }

    public void getAllSubSeq(int[] nums, List<Integer> temp, int idx) {
        System.out.println(temp);

        for (int i = idx; i < nums.length; i++) {
            temp.add(nums[i]);
            getAllSubSeq(nums, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        getAllSubSeq(nums, new LinkedList<>(), 0);
    }


    //Method 2: DP
    /*
    这道题给了我们一个数组，问我们这个数组能不能分成两个非空子集合，使得两个子集合的元素之和相同。
    那么我们想，原数组所有数字和一定是偶数，不然根本无法拆成两个和相同的子集合，
    那么我们只需要算出原数组的数字之和，然后除以2，就是我们的target，
    那么问题就转换为能不能找到一个非空子集合，使得其数字之和为target。
    定义一个一维的dp数组，其中dp[i]表示原数组是否可以取出若干个数字，其和为i。
    那么我们最后只需要返回dp[target]就行了。
    初始化dp[0]为true，由于题目中限制了所有数字为正数，那么就不用担心会出现和为0或者负数的情况。
    关键问题就是要找出状态转移方程了，我们需要遍历原数组中的数字，
    对于遍历到的每个数字nums[i]，需要更新dp数组，
    我们的最终目标是想知道dp[target]的boolean值，就要想办法用数组中的数字去凑出target，
    因为都是正数，所以只会越加越大，那么加上nums[i]就有可能会组成区间 [nums[i], target] 中的某个值，
    那么对于这个区间中的任意一个数字j，
    如果 dp[j - nums[i]] 为true的话，
    说明现在已经可以组成 j-nums[i] 这个数字了，再加上nums[i]，就可以组成数字j了，
    那么dp[j]就一定为true。
    如果之前dp[j]已经为true了，当然还要保持true，所以还要‘或’上自身，于是状态转移方程如下：

    dp[j] = dp[j] || dp[j - nums[i]]         (nums[i] <= j <= target)

    有了状态转移方程，那么我们就可以写出代码了，这里需要特别注意的是，
    第二个for循环一定要从target遍历到nums[i]，而不能反过来，想想为什么呢？
    因为如果我们从nums[i]遍历到target的话，
    假如nums[i]=1的话，那么[1, target]中所有的dp值都是true，
    因为dp[0]是true，dp[1]会或上dp[0]，为true，dp[2]会或上dp[1]，为true，依此类推，
    完全使我们的dp数组失效了
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;

        int n = nums.length;
        boolean[] dp = new boolean[sum + 1];
        Arrays.fill(dp, false);
        dp[0] = true;

        for (int num : nums) {
            for (int i = sum; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }

        return dp[sum];
    }

    // add by tina, memoSearch,将这道题理解成
    // 在n个物品中，找到组合去【填满】容量为sum/2的背包问题，使用背包问题的标准模板
    /// Memory Search
    /// Time Complexity: O(len(nums) * O(sum(nums)))
    /// Space Complexity: O(len(nums) * O(sum(nums)))
    // 状态定义：从[0,...,n-1]中找到物品组合，填满容量为sum/2的背包
    // F(i,c) = F(i-1,c) || F(i-1,c-nums(i-1))
    private int[][] memo;

    public boolean canPartition3(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) return false;
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) return false;
        memo = new int[n][sum / 2 + 1];
        return memoSearch(nums, n - 1, sum / 2);

    }

    // 从[0,...,index]范围内找到数，使其装满容量c的背包
    public boolean memoSearch(int[] nums, int index, int c) {
        if (index < 0 || c < 0) return false;
        if (c == 0) return true;
        if (memo[index][c] != 0) return memo[index][c] == 1;

        boolean res = memoSearch(nums, index - 1, c) ||
                memoSearch(nums, index - 1, c - nums[index]);
        memo[index][c] = res ? 1 : -1;
        return res;
    }

    //add by tina, DP
    // Time Complexity: O(len(nums) * O(sum(nums)))
    // Space Complexity: O(len(nums) * O(sum(nums)))
    // 函数中会写2个优化，
    // 一个是将Space Complexity优化到O(2*C),与物品个数没有关系
    // 进一步优化成O(C)
    public boolean canPartition4(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) return false;
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) return false;
        int c = sum / 2;
       /* boolean[][] dp = new boolean[n][c+1];
        // 因为从状态方程可以看出，i的状态只和i-1相关
        //初始化，考虑把编号为0的物品(数据)，装满容量为j的背包
        for(int j = 0;j<=c;j++){
            if(j==nums[0]) dp[0][j] = true;
            else dp[0][j] = false;
        }

        for(int i = 1;i<n;i++){
            for(int j = 0;j<=c;j++){
                dp[i][j] = dp[i-1][j];
                if(j>=nums[i]) dp[i][j] = dp[i-1][j]||dp[i-1][j-nums[i]];
            }
        }
        return dp[n-1][c];*/

        /* Space Complexity优化到O(2*C)，奇数行和偶数行交替赋到dp[1],dp[0]
        详细原理，参考bobo视频
        boolean[][] dp = new boolean[2][c+1];
        // 因为从状态方程可以看出，i的状态只和i-1相关
        //初始化，考虑把编号为0的物品(数据)，装满容量为j的背包
        for(int j = 0;j<=c;j++){
            if(j==nums[0]) dp[0][j] = true;
            else dp[0][j] = false;
        }

        for(int i = 1;i<n;i++){
            for(int j = 0;j<=c;j++){
                dp[i%2][j] = dp[(i-1)%2][j];
                if(j>=nums[i]) dp[i%2][j] = dp[(i-1)%2][j]||dp[(i-1)%2][j-nums[i]];
            }
        }
        return dp[(n-1)%2][c];*/

        // Space Complexity优化到O(C)，
        // 还是双重循环，注意从后往前刷新一维数组
        boolean[] dp = new boolean[c + 1];
        // 因为从状态方程可以看出，i的状态只和i-1相关
        //初始化，考虑把编号为0的物品(数据)，装满容量为j的背包
        for (int j = 0; j <= c; j++) {
            dp[j] = j == nums[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = c; j >= 0; j--) {
                if (j >= nums[i]) return dp[j] || dp[j - nums[i]];
            }
        }
        return dp[c];


    }

}
