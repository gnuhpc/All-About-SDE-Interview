package org.gnuhpc.bigdata.leetcode;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Arrays;

public class ContainerWithMostWater11 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,4,3};
        System.out.println(findContainer(nums));

    }

    private static int findContainer(int[] height) {
        int i = 0, j = height.length-1;
        int max = 0;

        while(i<j){
            int area = (Math.min(height[i],height[j]))*(j-i);
            if(area > max){
                max = area;
            } else {
                if(height[i]<height[j]){
                    i++;
                } else if (height[i]>height[j]){
                    j--;
                } else{
                    i++;
                    j--;
                }
            }

        }

        return max;
    }
}
