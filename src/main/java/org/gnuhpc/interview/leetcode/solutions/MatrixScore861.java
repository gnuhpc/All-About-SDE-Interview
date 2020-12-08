package org.gnuhpc.interview.leetcode.solutions;

public class MatrixScore861 {
    public int matrixScore(int[][] A) {
        int num1 = 0;
        int num0 = 0;
        for(int i = 0;i < A.length;i++){
            if(A[i][0] == 0){
                flip(A[i]);
            }
        }
        for(int j = 1;j < A[0].length;j++){
            num1 = 0;
            num0 = 0;
            for(int i = 0;i<A.length;i++){
                if(A[i][j] == 0){
                    num0++;
                }else{
                    num1++;
                }
            }
            if(num0 > num1){
                for(int i = 0;i<A.length;i++){
                    if(A[i][j] == 0){
                        A[i][j] = 1;
                    }else{
                        A[i][j] = 0;
                    }
                }
            }
        }
        num0 =  0;
        for(int i = 0; i < A.length;i++){
            num0 += valueOf(A[i]);
        }
        return num0;
    }

    public void flip(int[] num) {
        for (int i = 0; i < num.length; i++) {
            num[i] = 1 - num[i];
        }
    }
    public int valueOf(int[] num){
        int res = 0;
        for(int i = 0;i < num.length;i++){
            res = res * 2 + num[i];
        }
        return res;
    }
/*
作者：ni-huai
链接：https://leetcode-cn.com/problems/score-after-flipping-matrix/solution/jian-dan-si-lu-jie-da-by-ni-huai-i8e1/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
