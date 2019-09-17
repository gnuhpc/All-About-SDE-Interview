package org.gnuhpc.bigdata.designpattern.singleton.threadsafe;

import org.apache.derby.jdbc.EmbeddedDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
饿汉式双重检验锁
 */
public class DBSingleton{

	/*
	the field needs to be volatile to prevent cache incoherence issues.
	In fact, the Java memory model allows the publication of partially initialized objects
	and this may lead in turn to subtle bugs.
	 */
	private static volatile DBSingleton instance = null;

	// 不是static，是因为必须有一个这个类的实例才能获取连接
	private volatile Connection conn = null;


	private DBSingleton() {
		try {
			DriverManager.registerDriver(new EmbeddedDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DBSingleton getInstance() {
		if(instance == null) {
			synchronized(DBSingleton.class) {
				if (instance == null) {
					instance = new DBSingleton();
				}
			}
		}

		return instance;
	}

	public Connection getConnection() {
		if(conn == null) {
			synchronized (DBSingleton.class) {
				if(conn == null) {
					try {
						String dbUrl = "jdbc:derby:memory:codejava/webdb;create=true";

						conn = DriverManager.getConnection(dbUrl);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return conn;
	}

}
