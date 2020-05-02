package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber202 {
    @Test
    public void test() {
        int i = 19;
        System.out.println(isHappy2(i));
    }

    public static boolean isHappy1(int n) {
        Set<Integer> resultSet = new HashSet<>();

        while (true) {
            int temp = calcHappy(n);
            if (resultSet.contains(temp)) {
                return false;
            } else {
                if (temp == 1) {
                    return true;
                } else {
                    resultSet.add(temp);
                    n = temp;
                }
            }
        }
    }

    private static int calcHappy(int i) {
        int sum = 0;

        while (i > 0) {
            sum += (i % 10) * (i % 10);
            i = i / 10;
        }

        return sum;
    }

    /**
     * 快慢指针
     */
    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy2(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

}
