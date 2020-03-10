package org.gnuhpc.interview.misc;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
一个典型的电话拨号盘如下：

1 2 3
4 5 6
7 8 9
* 0 #

手指在两个按键之间的移动距离被定义成这两个键的x、y坐标差的绝对值之和。比如，6到自身的距离是0，到3、5、9的距离是1，到2、4、8、#的距离是2，到1、7、0的距离是3，到*的距离是4。
现在要你算一下，拨一个号手指所需要移动的最小距离是多少？假设手指初始位置在“5”。

输入
一行，一个字符串，表示需要拨的电话号码。

输入约束
电话号码的每一位仅包含数字“0”到“9”，且总长度范围是 [3,20]

输出
一个整数，表示拨完这个号码手指最少需要移动的距离

例子
输入
911
输出
6
 */

public class DailDistance {
    //键盘对应坐标
    private static Map<Integer, int[]> coor = new HashMap<Integer, int[]>() {
        {
            put(1, new int[]{1, 1});
            put(2, new int[]{1, 2});
            put(3, new int[]{1, 3});
            put(4, new int[]{2, 1});
            put(5, new int[]{2, 2});
            put(6, new int[]{2, 3});
            put(7, new int[]{3, 1});
            put(8, new int[]{3, 2});
            put(9, new int[]{3, 3});
            put(10, new int[]{4, 1});//for *
            put(0, new int[]{4, 2});
            put(11, new int[]{4, 3});//for #
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
        for (char c : dials) {
            int num;
            if (c == '#') num = 11;
            else if (c == '*') num = 10;
            else num = Integer.parseInt(String.valueOf(c));
            int[] curCoor = coor.get(num);
            res += Math.abs(startCoor[0] - curCoor[0]) + Math.abs(startCoor[1] - curCoor[1]);

            startCoor = Arrays.copyOf(curCoor, 2);
        }

        return res;
    }
}
