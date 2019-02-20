package org.gnuhpc.bigdata.leetcode;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber202 {
    public static void main(String[] args) {
        int i = 19;
        System.out.println(isHappyNumber(i));
    }

    public static boolean isHappyNumber(int n){
        Set<Integer> resultSet = new HashSet<>();

        while(true){
            int temp = calcHappy(n);
            if (resultSet.contains(temp)){
                return false;
            } else {
                if(temp == 1){
                    return true;
                } else {
                    resultSet.add(temp);
                    n = temp;
                }
            }
        }
    }

    private static int calcHappy(int i){
        int sum = 0;

        while(i>0){
            sum +=(i%10)*(i%10);
            i = i/10;
        }

        return sum;
    }

    /**
     * 思路与上面类似，仅做备份
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        HashSet<Integer> s = new HashSet<Integer>();
        if(n==1) return true;
        int num = calc(n);
        while(!s.contains(num) && num != 1){
            s.add(num);
            num = calc(num);
        }
        if(num == 1) return true;
        else return false;
    }

    public int calc(int n){
        int sum = 0;
        while(n != 0){
            int d = n %10;
            sum+= d*d;
            n = n/10;
        }
        return sum;
    }
}
