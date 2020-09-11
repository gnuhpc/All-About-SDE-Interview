package org.gnuhpc.interview.leetcode.solutions;

import java.util.List;

class BinaryMatrix {
     public int get(int row, int col) {return 0;}
     public List<Integer> dimensions() {
         return null;
     }
};
public class LeftMostColumnWithOne1428 {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int res = Integer.MAX_VALUE;
        List<Integer> size = binaryMatrix.dimensions();
        int rows = size.get(0);
        int cols = size.get(1);

        for(int i = 0; i<rows; i++){
            int left = 0, right = cols -1;
            while(left +1 < right){
                int mid = (right - left)/2 + left;
                if(binaryMatrix.get(i,mid) == 1) right = mid;
                else left = mid;
            }

            if(binaryMatrix.get(i,left) == 1) res = Math.min(res, left);
            if(binaryMatrix.get(i,right) == 1) res = Math.min(res, right);
        }

        return res == Integer.MAX_VALUE? -1:res;

    }
}
