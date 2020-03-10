package org.gnuhpc.interview.leetcode.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class Read158 {
    int read4(char[] buf) {
        return 0;
    }

    ;
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    private int readBuffPtr = 0;
    private int readBuffCnt = 0;
    private char[] readBuffer = new char[4];

    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            //已经没有上次读的了就再读下一次
            if (readBuffPtr == 0) {
                readBuffCnt = read4(readBuffer);
            }
            //啥也读不到了就跳出去
            if (readBuffCnt == 0) break;

            //然后将读到的写入结果
            while (ptr < n && readBuffPtr < readBuffCnt) {
                buf[ptr++] = readBuffer[readBuffPtr++];
            }
            //如果发现用完这次读到的数据，则read buffer pointer reset.
            if (readBuffPtr == readBuffCnt) readBuffPtr = 0;
        }
        return ptr;
    }

    //add by tina
    Queue<Character> remain = new LinkedList<>();

    public int read2(char[] buf, int n) {
        int i = 0;
        // 队列不为空时，先读取队列中的暂存字符
        while (i < n && !remain.isEmpty()) {
            buf[i] = remain.poll();
            i++;
        }
        for (; i < n; i += 4) {
            char[] tmp = new char[4];
            int len = read4(tmp);
            // 如果读到字符多于我们需要的字符，需要暂存这些多余字符
            if (len > n - i) {
                System.arraycopy(tmp, 0, buf, i, n - i);
                // 把多余的字符存入队列中
                for (int j = n - i; j < len; j++) {
                    remain.offer(tmp[j]);
                }
                // 如果读到的字符少于我们需要的字符，直接拷贝
            } else {
                System.arraycopy(tmp, 0, buf, i, len);
            }
            // 同样的，如果读不满4个，说明数据已经读完，返回总所需长度和目前已经读到的长度的较小的
            if (len < 4) return Math.min(i + len, n);
        }
        // 如果到这里，说明都是完美读取，直接返回n
        return n;
    }

}
