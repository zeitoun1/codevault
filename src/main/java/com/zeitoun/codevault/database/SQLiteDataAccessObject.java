package com.zeitoun.codevault.database;

import com.zeitoun.codevault.codesnippet.dataaccess.SnippetRepository;

import java.sql.*;

/**
 * DataBase access object using SQLite.
 */
public class SQLiteDataAccessObject implements SnippetRepository {

    private final String table;
    private final Connection connection;


    public SQLiteDataAccessObject(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    @Override
    public void saveSnippet(String code, String name, String description, String language) {
       String query = "INSERT INTO " + table + " VALUES(?, ?, ?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, code);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, language);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public Boolean isMember(String name, String language) {
        String query = "SELECT * FROM " + table + " WHERE name=? AND language=?";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, language);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + table + " (code TEXT, name VARCHAR(30), description VARCHAR(100), language VARCHAR(30), PRIMARY KEY(name, language));" ;

        try(PreparedStatement statement = connection.prepareStatement(query);) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }


}
