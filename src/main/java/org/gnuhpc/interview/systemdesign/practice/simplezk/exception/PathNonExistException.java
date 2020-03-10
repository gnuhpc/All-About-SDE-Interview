package org.gnuhpc.interview.systemdesign.practice.simplezk.exception;

public class PathNonExistException extends ZkServerException {
    public PathNonExistException() {
        super("Exception occured: Parent is not exist.");
    }
}
