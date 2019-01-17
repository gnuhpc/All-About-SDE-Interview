package org.gnuhpc.bigdata.designpattern.observer.observer;

import org.gnuhpc.bigdata.designpattern.observer.concreteobserverable.Stock;

/**
 * Design Pattern series by http://java9s.com
 * @author java9s.com
 *
 */
// Java内置也有这个类
public interface Observer {
	
	void update(Stock stock);

}
