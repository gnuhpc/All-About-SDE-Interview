package org.gnuhpc.bigdata.systemdesign.mq.redis;

public interface Callback {
    void onMessage(String message);
}
