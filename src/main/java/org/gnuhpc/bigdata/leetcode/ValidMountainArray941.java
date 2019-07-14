package org.gnuhpc.bigdata.leetcode;

public class ValidMountainArray941 {
    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) return false;

        int idx1 = findFirstPoint(A,true,0);

        if(idx1 == -2 || idx1 ==-1 || idx1 == 0){
            return false;
        }

        System.out.println("idx1 = " + idx1);

        int idx2 = findFirstPoint(A,false, idx1);
        System.out.println("idx2 = " + idx2);

        if(idx2 == -2 || idx2>idx1) return false;
        if(idx2 == -1) return true;
        if(idx1 == idx2) return false;

        return true;
    }

    //查找第一个拐点
    private int findFirstPoint(int[] A, boolean isDown, int start){
        for(int i = start; i< A.length-1;i++){
            System.out.println(i);
            if(A[i] == A[i+1]){
                return -2; //特殊情况返回-2，代表fast fail
            }
            if(isDown){
                if(A[i]>A[i+1]){
                    return i;
                }
            } else {
                if(A[i]<A[i+1]){
                    return i;
                }
            }
        }

        return -1;
    }

    //Method2 : 双指针
    public boolean validMountainArray2(int[] A) {
        if (A == null || A.length < 3) return false;
        int lo = 0;
        int hi = A.length - 1;
        while (lo < A.length - 1) {
            if (A[lo + 1] > A[lo]) lo++;
            else break;
        }
        while (hi > 0) {
            if (A[hi - 1] > A[hi]) hi--;
            else break;
        }

        return lo > 0 && hi < A.length - 1 && lo == hi;
    }

}
