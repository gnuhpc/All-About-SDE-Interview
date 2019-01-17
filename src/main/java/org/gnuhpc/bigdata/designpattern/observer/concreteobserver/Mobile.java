package org.gnuhpc.bigdata.designpattern.observer.concreteobserver;

import org.gnuhpc.bigdata.designpattern.observer.observer.Observer;
import org.gnuhpc.bigdata.designpattern.observer.concreteobserverable.Stock;

public class Mobile implements Observer {

	@Override
	public void update(Stock stock) {
		System.out.println("MOBILE - The Price of "+stock.getName()+" has changed:"+stock.getPrice());
	}

}
