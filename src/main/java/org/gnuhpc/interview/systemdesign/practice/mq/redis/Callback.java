package org.gnuhpc.interview.systemdesign.practice.mq.redis;

public interface Callback {
    void onMessage(String message);
}
