package org.gnuhpc.bigdata.designpattern.chainofresponsibility.concretehandler;

import org.gnuhpc.bigdata.designpattern.chainofresponsibility.pojo.Request;
import org.gnuhpc.bigdata.designpattern.chainofresponsibility.pojo.RequestType;
import org.gnuhpc.bigdata.designpattern.chainofresponsibility.handler.Handler;

public class VP extends Handler {

	@Override
	public void handleRequest(Request request) {
		if(request.getRequestType() == RequestType.PURCHASE) {
			if(request.getAmount() < 1500) {
				System.out.println("VPs can approve purchases below 1500");
			}
			else {
				successor.handleRequest(request);
			}
		}
	}
}
