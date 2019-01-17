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
}
