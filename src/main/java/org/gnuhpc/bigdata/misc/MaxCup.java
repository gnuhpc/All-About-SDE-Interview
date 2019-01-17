package org.gnuhpc.bigdata.misc;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public class MaxCup {
    public static void main(String[] args) {
        int max=-Integer.MAX_VALUE;
        Scanner scanner = new Scanner(System.in);
        int[][] array = new int[6][6];
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                int num = scanner.nextInt();
                assert(num>=-9&&num<=9);
                array[i][j] = num;
            }
        }

        int sum=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++ ){
                int[] cup = new int[7];
                cup[0] = array[i][j];
                cup[1] = array[i][j+1];
                cup[2] = array[i][j+2];
                cup[3] = array[i+1][j+1];
                cup[4] = array[i+2][j];
                cup[5] = array[i+2][j+1];
                cup[6] = array[i+2][j+2];
                sum= Arrays.stream(cup).reduce(0,(x,y)->x+y);
                if(sum>max){
                    max = sum;
                }
            }
        }

        System.out.println(max);


    }
}
