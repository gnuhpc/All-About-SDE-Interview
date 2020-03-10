package org.gnuhpc.interview.systemdesign.practice.objectpool.test;

import org.gnuhpc.interview.systemdesign.practice.objectpool.core.BoundedBlockingPool;
import org.gnuhpc.interview.systemdesign.practice.objectpool.core.Pool;
import org.gnuhpc.interview.systemdesign.practice.objectpool.core.PoolFactory;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestMain {
    public static void main(String[] args) {
        BoundedBlockingPool<Connection> pool =
                (BoundedBlockingPool) PoolFactory.newBoundedBlockingPool(
                        3,
                        new JDBCConnectionFactory("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.50.229:3306/mysql",
                                "root", "root"),
                        new JDBCConnectionValidator());
        //do whatever you like

        Connection c1 = pool.get();
        System.out.println("connection established");
        Connection c2 = pool.get();
        System.out.println("connection established");
        Connection c3 = pool.get();
        System.out.println("connection established");
        Connection c4 = pool.get(10, TimeUnit.SECONDS);

        assertNull(c4);

        pool.release(c1);
        pool.release(c2);

        c4 = pool.get(10, TimeUnit.SECONDS);

        assertNotNull(c4);
    }
}
