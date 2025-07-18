package com.zeitoun.codevault.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DataBase access object using SQLite.
 */
public class SQLiteDataAccessObject implements SnippetRepository, FoldersRepository {

    private final String snippetsTable;
    private final String foldersTable;
    private final Connection connection;


    public SQLiteDataAccessObject(Connection connection, String snippetsTable, String foldersTable) {
        this.connection = connection;
        this.snippetsTable = snippetsTable;
        this.foldersTable = foldersTable;
    }

    @Override
    public void saveSnippet(String code, String name, String description, String language, String folder) {
       String query = "INSERT INTO " + snippetsTable + " VALUES(?, ?, ?, ?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, code);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, language);
            statement.setString(5, folder);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public Boolean isMember(String name, String folder) {
        String query = "SELECT * FROM " + snippetsTable + " WHERE name=? AND folder=?";

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, folder);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getSnippets(String selectedFolder) {
        List<String> result = new ArrayList<>();
        String query = "SELECT name FROM " + snippetsTable + " WHERE folder=?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, selectedFolder);
            ResultSet snippets = statement.executeQuery();
            while (snippets.next()) {
                result.add(snippets.getString("name"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void createSnippetsTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + snippetsTable + " (code TEXT, name VARCHAR(30), description VARCHAR(100), language VARCHAR(30), folder VARCHAR(30), PRIMARY KEY(name, folder));" ;

        try(PreparedStatement statement = connection.prepareStatement(query);) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void createFoldersTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + foldersTable + " (name VARCHAR(30));" ;
        try(PreparedStatement statement = connection.prepareStatement(query);) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void addFolder(String name) {
        String query = "INSERT INTO " + foldersTable + " VALUES(?);";
        try(PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return;
    }

    @Override
    public Boolean isMember(String name) {
        String query = "SELECT * FROM " + foldersTable + " WHERE name=?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getFolders() {
        List<String> result = new ArrayList<>();
        String query = "SELECT * FROM " + foldersTable;
        try(Statement statement = connection.createStatement()) {
            ResultSet folders = statement.executeQuery(query);
            while (folders.next()) {
                result.add(folders.getString("name"));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
