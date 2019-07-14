package org.gnuhpc.bigdata.leetcode;

/**
 * 采用递归的做法，发现超时 80/92
 */
public class JumpGame45 {
    private int minJump = Integer.MAX_VALUE;
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return 0;
        help(nums,nums.length,0,0,0);
        return minJump;
    }

    public void help(int[] nums, int n, int index, int step,int jumps){
        int newindex = index+step;
        if(newindex >= n-1) {
            minJump = Math.min(jumps,minJump);
            return;
        }else{
            if(jumps >= minJump) return;
            for(int i = 1; i<=nums[newindex];i++){
                help(nums,n,newindex,i,jumps+1);
            }
        }
        return;
    }


    public int jump2(int[] nums) {
        int n = nums.length;
        int ret = 0;
        int last = 0;
        int curr = 0;
        for (int i = 0; i < n; ++i) {
            if (i > last) {
                last = curr;
                ++ret;
            }
            curr = Math.max(curr, i+nums[i]);
        }

        return ret;
    }

}
