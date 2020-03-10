package org.gnuhpc.interview.systemdesign.practice.simplezk.exception;

public class InvalidPathFormatException extends ZkServerException {
    public InvalidPathFormatException() {
        super("Exception occured: Invalid path");
    }
}
