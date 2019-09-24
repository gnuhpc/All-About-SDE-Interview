package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ReverseBits190 {
    // you need treat n as an unsigned value
    private Map<Byte, Integer> cache = new HashMap<>();

    /**
     * O(1) Time, O(1) Space
     * Divide 32 bits into 4 bytes
     * Cache each byte and its reversed result in a hashmap
     * Check cache for result first instead of computing all
     */

    public int reverseBits(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) bytes[i] = (byte) ((n >>> 8 * i) & 0xFF);
        int res = 0;
        for (int i = 0; i < 4; i++) res = (res << 8) ^ Utils.reverseBytes(bytes[i]);
        return res;
    }

    /*
    Method2: 字符串操作
     */
    public int reverseBits2(int n) {
        String str = Integer.toBinaryString(n);
        StringBuilder sb = new StringBuilder(str).reverse();
        for(int i = 0; i< 32 - str.length();i++){
            sb.append('0');
        }
        String res = sb.toString();

        return (int)Long.parseLong(res,2);
    }




    @Test
    public void test() {
        System.out.println(reverseBits(5));
    }

}
