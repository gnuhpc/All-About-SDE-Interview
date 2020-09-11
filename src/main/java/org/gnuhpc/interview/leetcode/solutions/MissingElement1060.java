package org.gnuhpc.interview.leetcode.solutions;

public class MissingElement1060 {
    public int missingElement(int[] nums, int k) {
        int len=nums.length;
        if (len==0) return k;
        int p=0;
        //每一轮确定相邻两个数之间有多少个"miss"的值；
        while(k>0 && p<len-1){
            //相邻两个数的差+1就是miss掉的值的个数，将k减去该值就是往下还要探的"miss"值得个数
            k=k-(nums[p+1]-nums[p])+1;
            p++;
        }
        //如果遇到k<=0的情况，那说明miss的值的个数没有超出数组最大值，将当前指针p处的值加上k值并-1就是该值
        if (k<=0){
            return nums[p]+k-1;
        } else {
            //否则，说明指针到头了，k值还是>0；把数组最后一个值加上k即可得到结果
            return nums[len-1]+k;
        }
    }
}
