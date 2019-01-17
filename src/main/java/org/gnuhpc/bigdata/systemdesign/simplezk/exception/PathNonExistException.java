package org.gnuhpc.bigdata.systemdesign.simplezk.exception;

public class PathNonExistException extends ZkServerException{
    public PathNonExistException() {
        super("Exception occured: Parent is not exist.");
    }
}
