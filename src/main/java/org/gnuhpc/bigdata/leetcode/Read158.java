package org.gnuhpc.bigdata.leetcode;

public class Read158 {
    int read4(char[] buf){return  0;};
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];
    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            //已经没有上次读的了就再读下一次
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            //consumer all the result of last read
            if (buffPtr == buffCnt) buffPtr = 0;
        }
        return ptr;
    }

}
