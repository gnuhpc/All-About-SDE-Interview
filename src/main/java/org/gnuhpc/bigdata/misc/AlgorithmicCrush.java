package org.gnuhpc.bigdata.misc;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by huangpengcheng on 2017/2/19.
 *
 * you are adding sum to a[p] and adding negative sum at a[q+1].
 * which make sure that when you add element from a[p] to a[q] sum is added only once and it should be subtracted at a[q+1] as this sum span from p to q only.
 * Rest array element are either 0 or some other input sum. m
 * ax of addition will be output. refer to above code for p, q, and sum.
 */
public class AlgorithmicCrush {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int m = sc.nextInt();
        sc.nextLine();

        long[] array = new long[(int) (n+1)];
        int begin,end;
        long sum,max=0,x=0;
        for (int i = 0; i < m; i++) {
            begin = sc.nextInt();
            end = sc.nextInt();
            sum = sc.nextLong();
            array[begin] += sum;
            if(end+1<=n){
                array[end+1] -= sum;
            }
        }

        for (int i = 1; i < n+1; i++) {
            x += array[i];
            if (x>max){
                max = x;
            }
        }

        System.out.println(max);
    }
}
