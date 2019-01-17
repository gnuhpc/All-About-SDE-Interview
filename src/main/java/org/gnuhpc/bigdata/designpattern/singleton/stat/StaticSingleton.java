package org.gnuhpc.bigdata.designpattern.singleton.stat;

//饿汉式
public class StaticSingleton {
	private static StaticSingleton uniqueInstance = new StaticSingleton();
 
	private StaticSingleton() {}
 
	public static StaticSingleton getInstance() {
		return uniqueInstance;
	}
}
