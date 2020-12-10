package org.gnuhpc.interview.leetcode.solutions;

/**
 * Copyright gnuhpc 2020/12/10
 */
public class LemonadeChange860 {
    public boolean lemonadeChange(int[] bills) {
        if (bills.length == 0) return true;
        if (bills.length == 1) return bills[0] == 5;

        int count5 = 0;
        int count10 = 0;
        for (int bill : bills) {
            if (bill == 5)
                count5++;
            else if (bill == 10) {
                count10++;
                count5--;
            } else {
                if (count10 > 0) {
                    count10--;
                    count5--;
                } else
                    count5 -= 3;
            }
            //每进行完一次交易后，都检查一下手中5元和10元的数量
            if (count5 < 0 || count10 < 0)
                return false;
        }
        return true;


    }
}
