package org.gnuhpc.interview.leetcode.solutions;

public class DayOfTheWeek1185 {
    private int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String[] WEEK_DAYS_STR = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public String dayOfTheWeek(int day, int month, int year) {
        final int zeroWeekDay = 4;
        final int zeroYear = 1970;
        int learYearNum = calLearYearNum(zeroYear, year);
        int daysFromZero = (year - zeroYear) * 365;
        if (year > zeroYear) {
            daysFromZero += learYearNum;
        }
        daysFromZero += dayOfYear(year, month, day);
        int dayOfWeek = (daysFromZero - 1 + zeroWeekDay) % 7;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return WEEK_DAYS_STR[dayOfWeek - 1];
    }

    public int dayOfYear(int year, int month, int day) {
        int dayOfYear = day;
        for (int m = 0; m < month - 1; m++) {
            dayOfYear += MONTH_DAYS[m];
        }
        if (isLeapYear(year) && month > 2) {
            dayOfYear += 1;
        }
        return dayOfYear;
    }

    private boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else {
            return year % 4 == 0 && year % 100 != 0;
        }
    }

    // [startYear, endYear)
    private int calLearYearNum(int startYear, int endYear) {
        int cnt = 0;
        for (int y = startYear; y < endYear; y++) {
            if (isLeapYear(y)) {
                cnt++;
            }
        }
        return cnt;
    }
}
