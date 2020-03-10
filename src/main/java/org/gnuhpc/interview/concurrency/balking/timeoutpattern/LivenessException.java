package org.gnuhpc.interview.concurrency.balking.timeoutpattern;

public class LivenessException extends RuntimeException {
    public LivenessException(String msg) {
        super(msg);
    }
}
