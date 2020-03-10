package org.gnuhpc.interview.systemdesign.practice.simplezk.exception;

public abstract class ZkServerException extends Exception {
    public ZkServerException(String msg) {
        super(msg);
    }
}
