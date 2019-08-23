package org.gnuhpc.bigdata.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NthUglyNumber264 {
    /*
    The basic idea of this problem is to compute all the ugly numbers in sequence
    and count to the given number of k ugly numbers.
    The way I approached this problem is first I have a arraylist to store the ugly numbers
    in sequence. Then I declared three counter variables: a,b,and c which represent the corresponding index in the arraylist
    for the multiplier of 2,3,and 5. Since each previous ugly number times one of the multiplier will produce a new ugly number,
    I start from the starting index 0 and multiply the ugly number at that index with each multiplier
    and get the smallest product which is the next ugly number from the three.
    The corresponding multipliers' index will be incremented by one and
    we do this recursively until we have K ugly numbers.


     */
    public int nthUglyNumber(int n) {
        if (n <= 0) return 0;
        int a = 0, b = 0, c = 0;
        List<Integer> table = new ArrayList<>();
        table.add(1);
        while (table.size() < n) {
            int next_val = Math.min(table.get(a) * 2, Math.min(table.get(b) * 3, table.get(c) * 5));
            table.add(next_val);
            if (table.get(a) * 2 == next_val) a++;
            if (table.get(b) * 3 == next_val) b++;
            if (table.get(c) * 5 == next_val) c++;
        }
        return table.get(table.size() - 1);
    }


    @Test
    public void test() {
        System.out.println(nthUglyNumber(10));
    }
}
