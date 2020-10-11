package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/10/9
 */
public class DayOfYear1154 {
    public int dayOfYear(String date) {
        String s1 = date.substring(0, 4);
        int year = Integer.valueOf(s1);
        String s2 = date.substring(5, 7);
        int month = Integer.valueOf(s2);
        String s3 = date.substring(8, 10);
        int day = Integer.valueOf(s3);

        // 1    2     3   4   5   6   7    8   9   10  11   12
        // 31 29/28  31  30  31  30   31   31  30  31  30   31
        int[] arr1 = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};// 平
        int[] arr2 = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};// 润
        int[] arr = null;
        /*
        普通闰年：公历年份是4的倍数的，
        且不是100的倍数，为普通闰年（如2004年、2020年就是闰年）。

        世纪闰年：公历年份是整百数的，必须是400的倍数才是世纪闰年
        （如1900年不是世纪闰年，2000年是世纪闰年）。
         */
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            arr = arr2;
        } else arr = arr1;
        int res = 0;
        for (int i = 1; i < month; i++) {
            res += arr[i - 1];
        }
        res += day;
        return res;
    }
}
