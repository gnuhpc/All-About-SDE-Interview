package org.gnuhpc.bigdata.misc.array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public class RotateArray {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int d = scan.nextInt();
        scan.nextLine();

        int[] array = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        rotateArray(array,n,d);
    }

    private static void rotateArray(int[] array, int n, int d) {
        int[] tempArray = new int[n];
        for(int i=d;i<n;i++){
            tempArray[i-d] = array[i];
        }
        for(int i=0; i<d;i++){
            tempArray[n+i-d] = array[i];
        }

        Arrays.stream(tempArray).forEach(num->System.out.print(num+" "));
        System.out.println();

    }
}
