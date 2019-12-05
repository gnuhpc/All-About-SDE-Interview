package org.gnuhpc.bigdata.misc;

import scala.Char;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class U2124p17 {
    //键盘对应坐标
    private static Map<Integer, int[]> coor = new HashMap<Integer, int[]>(){
        {
            put(1,new int[]{1,1});
            put(2,new int[]{1,2});
            put(3,new int[]{1,3});
            put(4,new int[]{2,1});
            put(5,new int[]{2,2});
            put(6,new int[]{2,3});
            put(7,new int[]{3,1});
            put(8,new int[]{3,2});
            put(9,new int[]{3,3});
            put(10,new int[]{4,1});//for *
            put(0,new int[]{4,2});
            put(11,new int[]{4,3});//for #
        }
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dialStr = scanner.nextLine();

        System.out.println(minDistance(dialStr));
    }

    private static int minDistance(String dialStr) {
        int starting = 5;
        int[] startCoor = coor.get(starting);
        int res = 0;

        String str;
        char[] dials = dialStr.toCharArray();
        for(char c: dials){
            int num;
            if(c == '#') num = 11;
            else if(c == '*') num = 10;
            else num = Integer.parseInt(String.valueOf(c));
            int[] curCoor = coor.get(num);
            res += Math.abs(startCoor[0]-curCoor[0]) + Math.abs(startCoor[1]-curCoor[1]);

            startCoor = Arrays.copyOf(curCoor, 2);
        }

        return res;
    }
}
