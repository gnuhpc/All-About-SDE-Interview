package org.gnuhpc.interview.designpattern.chainofresponsibility.concretehandler;

import org.gnuhpc.interview.designpattern.chainofresponsibility.handler.Handler;
import org.gnuhpc.interview.designpattern.chainofresponsibility.pojo.Request;

public class CEO extends Handler {

    @Override
    public void handleRequest(Request request) {
        System.out.println("CEOs can approve anything they want");
    }
}
