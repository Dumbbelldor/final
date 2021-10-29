package org.example.controller.connection.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.controller.connection.ConnectionPool;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <p>An implementation of a {@link ConnectionPool} interface,
 * that handles operations of adding and removing
 * {@link ProxyConnection} elements out of the connection pool.</p>
 *
 * <p>The pool is presented as two {@link BlockingDeque} collections,
 * where all restricted by the {@link #INITIAL_POOL_SIZE} connections
 * are stored.</p>
 *
 * <p>When connection is being utilized, it removes itself from the
 * pool of available connections and puts into occupied. And vice versa,
 * when it is freed, returns to the active pool.</p>
 */
public enum BasicConnectionPool implements ConnectionPool {

    INSTANCE;

    private static final Logger log = LogManager.getLogger();

    private static final int INITIAL_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 15;

    private static final BlockingDeque<ProxyConnection> availableConnections;
    private static final BlockingDeque<ProxyConnection> occupiedConnections;

    static {
        availableConnections = new LinkedBlockingDeque<>(INITIAL_POOL_SIZE);
        occupiedConnections = new LinkedBlockingDeque<>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                availableConnections.add(DatabaseConnection.createConnection());
            } catch (SQLException e) {
                log.error("Error during connection creating", e);
            }
        }
    }

    /**
     * Utilizes the topmost available connection and
     * puts it to use.
     *
     * @return a proxy connection out of available ones
     *
     * @throws SQLException when failed to make a database connection
     */
    @Override
    public ProxyConnection getConnection() throws SQLException {
        if (availableConnections.isEmpty()) {
            if (occupiedConnections.size() < MAX_POOL_SIZE) {
                availableConnections.add(DatabaseConnection.createConnection());
            } else {
                log.error("Error occurred due to empty queue");
            }
        }

        ProxyConnection connection = availableConnections.remove();
        occupiedConnections.add(connection);
        return connection;
    }

    /**
     * Releases a busy proxy connection and returns it
     * back to the pool.
     *
     * @return true if liberation is successful
     */
    @Override
    public boolean releaseConnection(ProxyConnection connection) {
        if (occupiedConnections.isEmpty()) {
            log.error("No occupied connections");
        }

        availableConnections.add(connection);
        return occupiedConnections.remove(connection);
    }

    public void shutdown() throws SQLException {
        occupiedConnections.forEach(this::releaseConnection);
        for (ProxyConnection c : availableConnections) {
            c.actualClose();
        }
        availableConnections.clear();

        DriverManager.drivers().forEach(d -> {
            try {
                DriverManager.deregisterDriver(d);
            } catch (SQLException e) {
                log.error("Error");
            }
        });
    }
}
