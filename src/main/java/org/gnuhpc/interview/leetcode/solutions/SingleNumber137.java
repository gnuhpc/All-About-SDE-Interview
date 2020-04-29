package org.gnuhpc.interview.leetcode.solutions;

public class SingleNumber137 {
    /*
    将每个数想象成32位的二进制，对于每一位的二进制的1和0累加起来必然是3N或者3N+1，
    为3N代表目标值在这一位没贡献，3N+1代表目标值在这一位有贡献(=1)，然后将所有有贡献的位|起来就是结果。
    这样做的好处是如果题目改成K个一样，只需要把代码改成cnt%k，很通用
     */
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            int cnt = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & mask) != 0) {
                    cnt++;
                }
            }
            if (cnt % 3 != 0) {
                ret |= mask;
            }
        }
        return ret;
    }
}
