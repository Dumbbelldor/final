package org.example.controller.connection.impl;

import org.postgresql.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>Package private class for {@link BasicConnectionPool}
 * exclusive usage.</p>
 *
 * <p>Responsible for establishing a connection with the
 * specified database.</p>
 */
class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/web_project";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    private DatabaseConnection() {}

    /**
     * Creates connection to the database.
     *
     * @return an established connection
     *
     * @throws SQLException when failed to connect to the database
     */
    static ProxyConnection createConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        return new ProxyConnection(DriverManager.getConnection(URL, USER, PASSWORD));
    }
}