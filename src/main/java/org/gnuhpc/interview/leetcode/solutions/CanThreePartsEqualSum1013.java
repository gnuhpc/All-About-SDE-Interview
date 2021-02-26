package org.gnuhpc.interview.leetcode.solutions;

public class CanThreePartsEqualSum1013 {
    public boolean canThreePartsEqualSum(int[] A) {
        int len=A.length;
        int sum=0;
        for(int i=0;i<len;i++){
            sum+=A[i];
        }
        //数组所有元素和不能被3整除，不满足等分要求
        if(sum%3!=0){
            return false;
        }
        int subSum=sum/3;
        //左右指针和左右等分和
        int left=1,leftSum=A[0],right=len-2,rightSum=A[len-1];
        //移动左边指针使左边子数组等于等分和
        while(leftSum!=subSum&&left<len){
            leftSum+=A[left];
            left++;
        }
        //移动右边指针使右边子数组等于等分和
        while(rightSum!=subSum&&right>=0){
            rightSum+=A[right];
            right--;
        }
        //此时左右指针中间还有剩余元素说明数组满足等分要求
        return left<=right;
    }
}
