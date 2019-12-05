package org.gnuhpc.bigdata.misc;

import java.io.*;
import java.util.Scanner;

public class U2124p2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        if (n<1 || n >= Integer.MAX_VALUE) return;

        if(isPowerTwo(n)){
            System.out.println("1");
        } else{
            System.out.println("0");
        }
    }

    private static boolean isPowerTwo(int n) {
        if (n == 1) return true;
        else {
            if (n%2==0){
                return isPowerTwo(n/2);
            } else{
                return false;
            }
        }
    }

}
