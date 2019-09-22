package org.gnuhpc.bigdata.leetcode;

public class IsUgly263 {
    public boolean isUgly(int num) {
        if (num<=0) return false;
        if (num<=6) return true;

        if (num%2 ==0) return isUgly(num/2);
        if (num%3 ==0) return isUgly(num/3);
        if (num%5 ==0) return isUgly(num/5);
        else return false;
    }
}
