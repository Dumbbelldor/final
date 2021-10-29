package org.example.controller.connection.impl;

import org.example.controller.connection.ConnectionPool;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.*;

public class BasicConnectionPoolTest {

    private static final ConnectionPool pool = BasicConnectionPool.INSTANCE;

    @Test
    public void testGetConnection() throws SQLException {
        ProxyConnection connection = pool.getConnection();
        assertTrue(connection.isValid(100));
    }

    @Test
    public void testReleaseConnection() throws SQLException {
        ProxyConnection connection = pool.getConnection();
        assertTrue(pool.releaseConnection(connection));
    }
}