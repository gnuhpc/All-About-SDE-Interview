package org.gnuhpc.bigdata.systemdesign.simplezk.exception;

public class NodeAlreadyExistException extends ZkServerException{
    public NodeAlreadyExistException() {
        super("Exception occured: Node is already exist");
    }
}
