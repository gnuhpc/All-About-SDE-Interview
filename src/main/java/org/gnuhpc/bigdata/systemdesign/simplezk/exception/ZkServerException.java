package org.gnuhpc.bigdata.systemdesign.simplezk.exception;

public abstract class ZkServerException extends Exception{
    public ZkServerException(String msg){
        super(msg);
    }
}
