package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.Arrays;

public class Read157 {
    int read4(char[] buf) {return 0;}

    ;

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int idx = 0;

        char[] temp = new char[4];
        while (idx < n) {
            int c = read4(temp);
            for (int i = 0; i < c && idx < n; i++) {
                buf[idx++] = temp[i];
            }
            if (c < 4) break; //read at the end
        }

        return idx;
    }


    /*
    Method2: 157/158 通解
    双指针+计数器
     */
    private int    buffPtr = 0;
    private int    buffCnt = 0;
    private char[] buff    = new char[4];

    public int read2(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr == buffCnt) buffPtr = 0;
        }
        return ptr;
    }

    //add by tina,System.arraycopy
    public int read3(char[] buf, int n) {
        for (int i = 0; i < n; i += 4) {
            char[] tmp = new char[4];
            // 将数据读入临时数组
            int len = read4(tmp);
            // 将临时数组拷贝至buf数组，这里拷贝的长度是本次读到的个数和剩余所需个数中较小的
            System.arraycopy(tmp, 0, buf, i, Math.min(len, n - i));
            // 如果读不满4个，说明已经读完了，返回总所需长度和目前已经读到的长度的较小的
            if (len < 4) return Math.min(i + len, n);
        }
        // 如果循环内没有返回，说明读取的字符是4的倍数
        return n;
    }

}
