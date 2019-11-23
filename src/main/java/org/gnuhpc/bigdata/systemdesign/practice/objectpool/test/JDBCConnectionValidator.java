package org.gnuhpc.bigdata.systemdesign.practice.objectpool.test;

import org.gnuhpc.bigdata.systemdesign.practice.objectpool.core.Pool;

import java.sql.Connection;
import java.sql.SQLException;

public final class JDBCConnectionValidator implements Pool.Validator<Connection> {
    public boolean isValid(Connection con) {
        if (con == null) {
            return false;
        }

        try {
            return !con.isClosed();
        } catch (SQLException se) {
            return false;
        }

    }

    public void invalidate(Connection con) {
        try {
            con.close();
        } catch (SQLException se) {
        }
    }
}
