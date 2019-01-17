package org.gnuhpc.bigdata.designpattern.observer.concreteobserverable;

import lombok.Data;
import org.gnuhpc.bigdata.designpattern.observer.observer.Observer;
import org.gnuhpc.bigdata.designpattern.observer.observerable.Observable;

import java.util.ArrayList;
import java.util.List;
/**
 * Design Pattern series by http://java9s.com
 * @author java9s.com
 *
 */

@Data
public abstract class Stock implements Observable {
	private List<Observer> observers;
	
	public Stock(){
		observers = new ArrayList<>();
	}
	
	private String name;
	private int price;
	
	@Override
	public void registerObserver(Observer observer) {
		if(observer != null){
			this.observers.add(observer);
		}
	}

	//Push到监听者那边做处理
	@Override
	public void notifyObservers() {
		for(Observer o : observers){
			o.update(this);
		}
	}

	@Override
	public void removeObserver(Observer observer) {
		if(observer != null){
			this.observers.remove(observer);
		}
	}

	//分两步，第一步更新，第二步通知
	public void updateStockPrice(int updatedPrice){
		this.price = updatedPrice;
		this.notifyObservers();
	}
}
