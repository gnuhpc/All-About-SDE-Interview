package org.gnuhpc.interview.concurrency.threadlocal;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TSLog {
    private PrintWriter writer = null;

    // 初始化writer字段
    public TSLog(String filename) {
        try {
            writer = new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 写日志
    public void println(String s) {
        writer.println(s);
    }

    // 关闭日志
    public void close() {
        writer.println("==== End of log ====");
        writer.close();
    }
}
