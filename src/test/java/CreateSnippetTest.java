import com.zeitoun.codevault.codesnippet.createsnippet.CreateCodeSnippetInputData;
import com.zeitoun.codevault.codesnippet.createsnippet.CreateCodeSnippetInteractor;
import com.zeitoun.codevault.codesnippet.createsnippet.CreateCodeSnippetOutputBoundary;
import com.zeitoun.codevault.database.SQLiteConnectionManager;
import com.zeitoun.codevault.database.SQLiteDataAccessObject;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreateSnippetTest {

    private Connection connection;


    @Test
    void successTest() {
        // creating test table
        String jdbcURL = "jdbc:sqlite:test.db";

        SQLiteConnectionManager sqLiteConnectionManager = new SQLiteConnectionManager();
        this.connection = sqLiteConnectionManager.setConnection(jdbcURL);
        SQLiteDataAccessObject sqLiteDataAccessObject = new SQLiteDataAccessObject(connection, "test");
        sqLiteDataAccessObject.createTable();


        CreateCodeSnippetOutputBoundary createCodeSnippetOutputBoundary = new CreateCodeSnippetOutputBoundary() {
            @Override
            public void SwitchToHomeView() {
                String query = "SELECT * FROM test WHERE language = 'python' AND name = 'empty code snippet'";
                try(PreparedStatement statement = connection.prepareStatement(query);){
                    ResultSet result = statement.executeQuery();
                    // check if the snippet has been saved correctly
                    assertTrue(result.getString("language").equals("python") && result.getString("name").equals("empty code snippet"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void showErrorMessage(String error) {
                fail("Use case showErrorMessage is unexpected.");
            }

            @Override
            public void showReplaceConfirmation(String message) {
                fail("Use case replaceConfirmation is unexpected.");
            }
        };

        CreateCodeSnippetInteractor createCodeSnippetInteractor = new CreateCodeSnippetInteractor(sqLiteDataAccessObject, createCodeSnippetOutputBoundary);
        CreateCodeSnippetInputData inputData = new CreateCodeSnippetInputData("int main(){}",
                "empty code snippet", "an empty python main function", "python");

        createCodeSnippetInteractor.execute(inputData);
    }

    @AfterEach
    public void deleteTestDB() {
        try {
            String dropTestDB = "DROP TABLE IF EXISTS test";
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTestDB);
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

