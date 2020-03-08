package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2019/10/5
 */
public class Logger359 {
    Map<String, Integer> record;

    /**
     * Initialize your data structure here.
     */
    public Logger359() {
        record = new HashMap<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed.
     * The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        boolean couldPrint = false;
        Integer lastTime = record.get(message);
        if (lastTime == null) {
            record.put(message, timestamp);
            couldPrint = true;
        }
        else {
            if (timestamp - 10 >= lastTime) {
                record.put(message, timestamp);
                couldPrint = true;
            }
            else {
                couldPrint = false;
            }
        }

        return couldPrint;
    }
}
