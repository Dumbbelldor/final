package org.example.controller.connection;

import org.example.controller.connection.impl.ProxyConnection;

import java.sql.SQLException;

/**
 * An interface for handling general
 * database connection pool operations.
 */
public interface ConnectionPool {

    /**
     * Takes a proxy connection from the pool
     * and utilizes it.
     */
    ProxyConnection getConnection() throws SQLException;

    /**
     * Releases a busy proxy connection and returns it
     * back to the pool.
     */
    boolean releaseConnection(ProxyConnection connection);
}