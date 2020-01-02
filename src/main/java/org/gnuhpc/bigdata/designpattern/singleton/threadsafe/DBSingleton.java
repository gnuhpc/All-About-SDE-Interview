package org.gnuhpc.bigdata.designpattern.singleton.threadsafe;

import org.apache.derby.jdbc.EmbeddedDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
饿汉式双重检验锁
假如在某一瞬间线程A和线程B都在调用getInstance()方法，此时instance对象为null值，
均能通过instance == null的判断。由于实现了synchronized加锁机制，
线程A进入synchronized锁定的代码中执行实例创建代码，线程B处于排队等待状态，
必须等待线程A执行完毕后才可以进入synchronized锁定代码。但当A执行完毕时，线程B并不知道实例已经创建，
将继续创建新的实例，导致产生多个单例对象，违背单例模式的设计思想，因此需要进行进一步改进，
在synchronized中再进行一次(instance == null)判断，
这种方式称为双重检查锁定(Double-Check Locking)。

 */
public class DBSingleton{

	/*
	the field needs to be volatile to prevent cache incoherence issues.
	In fact, the Java memory model allows the publication of partially initialized objects
	and this may lead in turn to subtle bugs.
	 */
	//要点1：private static volatile
	private static volatile DBSingleton instance = null;

	// 不是static，是因为必须有一个这个类的实例才能获取连接
	private volatile Connection conn = null;


	//要点2: private constructor
	private DBSingleton() {
		try {
			DriverManager.registerDriver(new EmbeddedDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static DBSingleton getInstance() {
		//要点三： 判断null
		if(instance == null) {
			//要点四： synchronized
			synchronized(DBSingleton.class) {
				if (instance == null) {//要点五： double check
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
