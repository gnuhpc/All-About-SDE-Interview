package org.gnuhpc.bigdata.designpattern.facade;

import java.util.List;

/*
门面模式，这个模式最简单最常用，一个门面，包含所有想协同的类作为成员变量，然后对外提供接口。
 */
public class MainApp {

	public static void main (String args []) {
		JdbcFacade jdbcFacade = new JdbcFacade();
		jdbcFacade.createTable();
		System.out.println("Table created.");
		jdbcFacade.insertIntoTable();
		System.out.println("Record inserted.");
	}
}

