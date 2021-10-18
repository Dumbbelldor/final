package org.example.controller.connection.impl;

import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.*;

public class BasicConnectionPoolTest {

    @Test
    public void testGetConnection() throws SQLException {
        BasicConnectionPool pool = BasicConnectionPool.INSTANCE;
        System.out.println("Occ "+pool.getOccupiedConSize()+" free "+pool.getAvailableConSize());
        ProxyConnection connection = pool.getConnection();
        ProxyConnection connection1 = pool.getConnection();
        ProxyConnection connection2 = pool.getConnection();
        ProxyConnection connection3 = pool.getConnection();
        System.out.println("Occ "+pool.getOccupiedConSize()+" free "+pool.getAvailableConSize());
        pool.releaseConnection(connection1);
        System.out.println("Occ "+pool.getOccupiedConSize()+" free "+pool.getAvailableConSize());

    }
}