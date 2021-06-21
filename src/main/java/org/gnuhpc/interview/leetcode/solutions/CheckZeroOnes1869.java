package org.gnuhpc.interview.leetcode.solutions;

public class CheckZeroOnes1869 {
    public boolean checkZeroOnes(String s) {
        int oneMax = -1, zeroMax = -1, oneCnt = 0, zeroCnt = 0; // 初始化
        for(char ch: s.toCharArray()){
            if(ch == '1'){
                oneCnt++;
                oneMax = Math.max(oneMax, oneCnt); // 更新1的最大值
                zeroCnt = 0; // 注意重置zeroCnt
            }
            else{ // 如果是‘0’
                zeroCnt++;
                zeroMax = Math.max(zeroMax, zeroCnt);
                oneCnt = 0;
            }
        }
        return oneMax > zeroMax;

    }
}
