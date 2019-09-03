package org.gnuhpc.bigdata.systemdesign.practice.mq.redis;

public interface Callback {
    void onMessage(String message);
}
