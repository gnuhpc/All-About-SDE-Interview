package org.gnuhpc.bigdata.systemdesign.mq.redis;

public interface Callback {
    public void onMessage(String message);
}
