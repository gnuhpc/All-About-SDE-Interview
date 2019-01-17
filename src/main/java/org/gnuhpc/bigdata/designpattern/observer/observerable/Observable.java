package org.gnuhpc.bigdata.designpattern.observer.observerable;

import org.gnuhpc.bigdata.designpattern.observer.observer.Observer;

/**
 * Design Pattern series by http://java9s.com
 * @author java9s.com
 *
 */
// 有些书上叫做subject , Java内置也有这个类
public interface Observable {
	void registerObserver(Observer observer);
	void notifyObservers();
	void removeObserver(Observer observer);
}
