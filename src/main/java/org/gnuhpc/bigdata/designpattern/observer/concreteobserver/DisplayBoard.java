package org.gnuhpc.bigdata.designpattern.observer.concreteobserver;

import org.gnuhpc.bigdata.designpattern.observer.observer.Observer;
import org.gnuhpc.bigdata.designpattern.observer.concreteobserverable.Stock;

/**
 * Design Pattern series by http://java9s.com
 * @author java9s.com
 *
 */
public class DisplayBoard implements Observer {

	@Override
	public void update(Stock stock) {
	System.out.println("DISPLAYBOARD - The Price of "+stock.getName()+" has changed:"+stock.getPrice());
	}

}
