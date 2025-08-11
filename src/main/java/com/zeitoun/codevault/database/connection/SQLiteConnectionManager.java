package com.zeitoun.codevault.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnectionManager {

    public Connection setConnection(String jdbcUrl) {
        try {
            return DriverManager.getConnection(jdbcUrl); // If the database file doesn't exist, it creates it first
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
