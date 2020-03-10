package org.gnuhpc.interview.systemdesign.practice.objectpool.test;

import org.gnuhpc.interview.systemdesign.practice.objectpool.core.ObjectFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionFactory implements ObjectFactory<Connection> {
    private String connectionURL;
    private String userName;
    private String password;

    public JDBCConnectionFactory(
            String driver,
            String connectionURL,
            String userName,
            String password) {
        super();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ce) {
            throw new IllegalArgumentException("Unable to find driver in classpath", ce);
        }
        this.connectionURL = connectionURL;
        this.userName = userName;
        this.password = password;
    }

    public Connection createNew() {
        try {
            return DriverManager.getConnection(
                    connectionURL,
                    userName,
                    password);
        } catch (SQLException se) {
            throw new IllegalArgumentException("Unable to create new connection", se);
        }
    }
}
