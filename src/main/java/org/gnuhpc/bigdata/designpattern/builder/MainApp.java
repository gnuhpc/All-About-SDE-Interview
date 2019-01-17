package org.gnuhpc.bigdata.designpattern.builder;

public class MainApp{

	public static void main(String args[]) {
		
		LunchOrder.Builder builder = new LunchOrder.Builder();
		
		builder.bread("Wheat").dressing("Mayo").meat("Turkey");
		
		LunchOrder lunchOrder = builder.build();
		
		System.out.println(lunchOrder.getBread());
		System.out.println(lunchOrder.getCondiments());
		System.out.println(lunchOrder.getDressing());
		System.out.println(lunchOrder.getMeat());	
	}
}
