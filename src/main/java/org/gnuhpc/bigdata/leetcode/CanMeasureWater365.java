package org.gnuhpc.bigdata.leetcode;

/**
 * Copyright gnuhpc 19-8-21
 */
public class CanMeasureWater365 {
    public int gcd(int p, int q) {
        if (p == 0) return q;
        int r = q % p;
        return gcd(r, p);
    }

    public boolean canMeasureWater(int x, int y, int z) {

        if (x + y < z) return false;
        if (x == z || y == z || x + y == z) return true;
        return z % gcd(x, y) == 0;

    }
}
