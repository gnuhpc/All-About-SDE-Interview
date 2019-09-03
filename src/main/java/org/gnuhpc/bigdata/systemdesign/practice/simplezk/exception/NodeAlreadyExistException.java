package org.gnuhpc.bigdata.systemdesign.practice.simplezk.exception;

public class NodeAlreadyExistException extends ZkServerException{
    public NodeAlreadyExistException() {
        super("Exception occured: Node is already exist");
    }
}
