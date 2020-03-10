package org.gnuhpc.interview.designpattern.chainofresponsibility.handler;

import org.gnuhpc.interview.designpattern.chainofresponsibility.pojo.Request;

public abstract class Handler {

    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(Request request);

}
