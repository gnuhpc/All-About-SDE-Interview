package org.gnuhpc.bigdata.designpattern.chainofresponsibility;


import org.gnuhpc.bigdata.designpattern.chainofresponsibility.concretehandler.CEO;
import org.gnuhpc.bigdata.designpattern.chainofresponsibility.concretehandler.Director;
import org.gnuhpc.bigdata.designpattern.chainofresponsibility.concretehandler.VP;
import org.gnuhpc.bigdata.designpattern.chainofresponsibility.pojo.Request;
import org.gnuhpc.bigdata.designpattern.chainofresponsibility.pojo.RequestType;

/*
责任链模式用于构建一个环节，所有实际要处理事务的handler都继承一个handler抽象类，
这个抽象类包含一个下一个要处理的handler的成员变量
在实际处理中，具体的handler根据请求的业务逻辑选择是自己处理还是传递到下一个环节中
 */
public class MainApp{

	public static void main(String[] args) {
		Director bryan = new Director();
		VP crystal = new VP();
		CEO jeff = new CEO();
		
		bryan.setSuccessor(crystal);
		crystal.setSuccessor(jeff);
		
		Request request = new Request(RequestType.CONFERENCE, 500);
		bryan.handleRequest(request);
		
		request = new Request(RequestType.PURCHASE, 1000);
		crystal.handleRequest(request);
		
		request = new Request(RequestType.PURCHASE, 2000);
		bryan.handleRequest(request);
	}
}
