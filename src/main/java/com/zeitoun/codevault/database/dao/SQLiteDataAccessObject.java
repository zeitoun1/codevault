package com.zeitoun.codevault.database.dao;

import com.zeitoun.codevault.codesnippet.entity.CodeSnippet;
import com.zeitoun.codevault.database.repostiories.FoldersRepository;
import com.zeitoun.codevault.database.repostiories.SnippetRepository;

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

    // snippet methods

    @Override
    public void saveSnippet(String code, String name, String description, String language, String folder) {
       String query = "INSERT INTO " + snippetsTable + " VALUES(?, ?, ?, ?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, code);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, language);
            statement.setString(5, folder);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public CodeSnippet getSnippet(String name, String selectedFolder) {
        String query = "SELECT code, description, language FROM " + snippetsTable + " WHERE name=? AND folder=?";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, name);
            statement.setString(2, selectedFolder);
            ResultSet result = statement.executeQuery();
            return new CodeSnippet(result.getString("code"), name, result.getString("description"), result.getString("language"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSnippet(String selectedSnippet, String selectedFolder, String code, String description, String language) {
        String query = "UPDATE " + snippetsTable + " SET code=?, description=?, language=? WHERE name=? AND folder=?";
        try (PreparedStatement statement =  connection.prepareStatement(query)) {
            statement.setString(1, code);
            statement.setString(2, description);
            statement.setString(3, language);
            statement.setString(4, selectedSnippet);
            statement.setString(5, selectedFolder);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void renameSnippet(String oldSnippetName, String newSnippetName, String selectedFolder) {
        String query = "UPDATE " + snippetsTable + " SET name=? WHERE name=? AND folder=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newSnippetName);
            statement.setString(2, oldSnippetName);
            statement.setString(3, selectedFolder);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSnippet(String selectedSnippet, String selectedFolder) {
        String query = "DELETE FROM " + snippetsTable + " WHERE name=? AND folder=?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, selectedSnippet);
            statement.setString(2, selectedFolder);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // folder methods

    @Override
    public void addFolder(String name) {
        String query = "INSERT INTO " + foldersTable + " VALUES(?);";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public void renameFolder(String oldName, String newName) {
        String folderTableQuery = "UPDATE " + foldersTable + " SET name=? WHERE name=?";
        String snippetsTableQuery = "UPDATE " + snippetsTable + " SET folder=? WHERE folder=?";

        try(PreparedStatement folderStatement = connection.prepareStatement(folderTableQuery);
            PreparedStatement snippetStatement = connection.prepareStatement(snippetsTableQuery))
        {
            connection.setAutoCommit(false);
            folderStatement.setString(1, newName);
            folderStatement.setString(2, oldName);
            snippetStatement.setString(1, newName);
            snippetStatement.setString(2, oldName);

            folderStatement.executeUpdate();
            snippetStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFolder(String selectedFolder) {
        String folderTableQuery = "DELETE FROM " + foldersTable + " WHERE name=?";
        String snippetsTableQuery = "DELETE FROM " + snippetsTable + " WHERE folder=?";

        try(PreparedStatement folderStatement = connection.prepareStatement(folderTableQuery);
            PreparedStatement snippetsStatement = connection.prepareStatement(snippetsTableQuery))
        {
            connection.setAutoCommit(false);
            folderStatement.setString(1, selectedFolder);
            snippetsStatement.setString(1, selectedFolder);

            folderStatement.executeUpdate();
            snippetsStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Helper methods

    public Connection getConnection() {
        return connection;
    }

    public void createSnippetsTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + snippetsTable + " (code TEXT, name VARCHAR(30), description VARCHAR(100), language VARCHAR(30), folder VARCHAR(30), PRIMARY KEY(name, folder));" ;

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void createFoldersTable() {
        String query = "CREATE TABLE IF NOT EXISTS " + foldersTable + " (name VARCHAR(30));" ;
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
