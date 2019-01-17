package org.gnuhpc.bigdata.designpattern.chainofresponsibility.concretehandler;

import org.gnuhpc.bigdata.designpattern.chainofresponsibility.handler.Handler;
import org.gnuhpc.bigdata.designpattern.chainofresponsibility.pojo.Request;

public class CEO extends Handler {

	@Override
	public void handleRequest(Request request) {
		System.out.println("CEOs can approve anything they want");
	}
}
