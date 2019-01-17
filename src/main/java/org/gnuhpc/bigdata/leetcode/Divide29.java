package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

/**
 * Description:
 * User: gnuhpc
 * Date: 2018-12-22 21:48
 * Version: 0.1
 */
public class Divide29 {
    public int divide(int dividend, int divisor) {
        long result = divideLong(dividend, divisor);
        return result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)result;
    }

    // It's easy to handle edge cases when
// operate with long numbers rather than int
    public long divideLong(long dividend, long divisor) {

        // Remember the sign
        boolean negative = dividend < 0 != divisor < 0;

        // Make dividend and divisor unsign
        if (dividend < 0) dividend = -dividend;
        if (divisor < 0) divisor = -divisor;

        // Return if nothing to divide
        // Base condition
        if (dividend < divisor) return 0;

        // Sum divisor 2, 4, 8, 16, 32 .... times
        long sum = divisor;
        long divide = 1;
        while ((sum+sum) <= dividend) {
            sum += sum;
            divide += divide;
        }

        // Make a recursive call for (devided-sum) and add it to the result
        long absRes = divide + divideLong((dividend-sum), divisor);
        return negative ? -1*absRes : absRes;
    }

    @Test
    public void test(){
        System.out.println(divide(10,3));
    }
}
