package org.gnuhpc.bigdata.misc;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main_a2_005 {
    public static int[] resArr = new int[100001];
    static{
        resArr[0] = 1;
        resArr[1] = 1;
        resArr[2] = 1;

        for (int i = 3; i < 100001; i++) {
            resArr[i] = (resArr[i-1] + resArr[i-2]) % 1000000;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DecimalFormat df=new DecimalFormat("000000");
        while(scan.hasNextInt()){
            int n = scan.nextInt();
            int num = resArr[n];
            if(n < 25){
                System.out.println(num);
            }else{
                System.out.println(df.format(num));
            }
        }
    }

}
