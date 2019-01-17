package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

// 位操作基础如下：
// https://www.vojtechruzicka.com/bit-manipulation-java-bitwise-bit-shift-operations/
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




    @Test
    public void test() {
        System.out.println(reverseBits(5));
    }

}
