package com.zeitoun.codevault.app;

import com.zeitoun.codevault.codesnippet.createsnippet.*;
import com.zeitoun.codevault.codesnippet.dataaccess.SnippetRepository;
import com.zeitoun.codevault.database.SQLiteConnectionManager;
import com.zeitoun.codevault.database.SQLiteDataAccessObject;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import java.awt.*;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.Arrays;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String jdbcURL = "jdbc:sqlite:test.db";
        ObservableList<String> languages = FXCollections.observableArrayList();
        languages.setAll(Arrays.asList("c", "c++", "python", "java"));
        SQLiteConnectionManager sqLiteConnectionManager = new SQLiteConnectionManager();
        Connection connection = sqLiteConnectionManager.setConnection(jdbcURL);
        SQLiteDataAccessObject sqLiteDataAccessObject = new SQLiteDataAccessObject(connection, "test");
        sqLiteDataAccessObject.createTable();
        CreateCodeSnippetViewModel viewModel = new CreateCodeSnippetViewModel(languages);
        CreateCodeSnippetPresenter presenter = new CreateCodeSnippetPresenter(viewModel);

        CreateCodeSnippetInteractor interactor = new CreateCodeSnippetInteractor(sqLiteDataAccessObject, presenter);
        CreateCodeSnippetController controller = new CreateCodeSnippetController(interactor);
        CreateCodeSnippetView view = new CreateCodeSnippetView(controller, viewModel);
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Code Vault");
        primaryStage.show();
    }

}

