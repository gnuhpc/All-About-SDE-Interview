package org.gnuhpc.bigdata.leetcode;

import java.util.Arrays;

public class Read157 {
    int read4(char[] buf){return  0;};
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int idx =0;

        char[] temp = new char[4];
        while(idx < n){
            int c = read4(temp);
            for(int i=0; i<c && idx < n; i++){
                buf[idx++] = temp[i];
            }
            if(c < 4) break; //read at the end
        }

        return idx;
    }


    /*
    Method2: 157/158 通解
     */
    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];
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

}
