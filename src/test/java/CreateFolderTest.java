import static org.junit.jupiter.api.Assertions.*;

import com.zeitoun.codevault.database.SQLiteConnectionManager;
import com.zeitoun.codevault.database.SQLiteDataAccessObject;
import com.zeitoun.codevault.folder.createfolder.usecase.CreateFolderInteractor;
import com.zeitoun.codevault.folder.createfolder.usecase.CreateFolderOutputBoundary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.*;


public class CreateFolderTest {

    private Connection connection;

    @Test
    void successTest() {
        // creating test table
        String jdbcURL = "jdbc:sqlite:test.db";

        SQLiteConnectionManager sqLiteConnectionManager = new SQLiteConnectionManager();
        this.connection = sqLiteConnectionManager.setConnection(jdbcURL);
        SQLiteDataAccessObject sqLiteDataAccessObject = new SQLiteDataAccessObject(connection, "snippetsTest", "foldersTest");
        sqLiteDataAccessObject.createFoldersTable();

        CreateFolderOutputBoundary createFolderOutputBoundary = new CreateFolderOutputBoundary() {
            @Override
            public void updateFoldersList(String folder) {
                String query = "SELECT * FROM foldersTest WHERE name = 'java snippets'";
                try(PreparedStatement statement = connection.prepareStatement(query);){
                    ResultSet result = statement.executeQuery();
                    // check if the snippet has been saved correctly
                    assertEquals("java snippets", result.getString("name"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void showErrorMessage(String error) {
                fail("Use case showErrorMessage is unexpected.");
            }
        };


        CreateFolderInteractor createFolderInteractor = new CreateFolderInteractor(sqLiteDataAccessObject, createFolderOutputBoundary);
        createFolderInteractor.execute("java snippets");
    }



    @AfterEach
    public void deleteTestDB() {
        try {
            String dropTestDB = "DROP TABLE IF EXISTS FoldersTest";
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTestDB);
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
