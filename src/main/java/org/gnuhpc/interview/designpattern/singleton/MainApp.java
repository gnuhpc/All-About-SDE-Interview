package org.gnuhpc.interview.designpattern.singleton;

import org.gnuhpc.interview.designpattern.singleton.stat.StaticSingleton;
import org.gnuhpc.interview.designpattern.singleton.threadsafe.DBSingleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*
单例模式，为了全局只有一个。

要点1：private构造函数
要点2：private static 成员变量
要点3：创建getInstanc 这个static方法

与factory比起来不同之处在于单例不要有参数，没有接口，而factory返回多种实例，有接口
* */
public class MainApp {
    public static void main(String[] args) {
        StaticSingleton staticSingleton = StaticSingleton.getInstance();

        long timeBefore = 0;
        long timeAfter = 0;

        DBSingleton instance = DBSingleton.getInstance();

        timeBefore = System.currentTimeMillis();
        Connection conn = instance.getConnection();
        timeAfter = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);


        Statement sta;
        try {
            sta = conn.createStatement();
            int count = sta
                    .executeUpdate("CREATE TABLE Address (ID INT, StreetName VARCHAR(20),"
                            + " City VARCHAR(20))");
            System.out.println("Table created.");
            sta.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        timeBefore = System.currentTimeMillis();
        conn = instance.getConnection();
        timeAfter = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);
    }
}
