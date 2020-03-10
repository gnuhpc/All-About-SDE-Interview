package org.gnuhpc.interview.algorithm.recursion.gcd;

public class GCD {

    public int gcdIterative(int num1, int num2) {

        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2; // modulo operator
            num1 = temp;
        }

        return num1;
    }

    public int gcdRecursive(int num1, int num2) {

        if (num2 == 0) return num1;

        return gcdRecursive(num2, num1 % num2);
    }
}
