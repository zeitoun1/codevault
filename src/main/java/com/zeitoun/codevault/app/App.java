package com.zeitoun.codevault.app;

import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetPresenter;
import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetController;
import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetInteractor;
import com.zeitoun.codevault.codesnippet.createsnippet.view.CreateCodeSnippetView;
import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetViewModel;
import com.zeitoun.codevault.database.SQLiteConnectionManager;
import com.zeitoun.codevault.database.SQLiteDataAccessObject;
import com.zeitoun.codevault.folderspane.interfaceadapter.FoldersPaneViewModel;
import com.zeitoun.codevault.folderspane.view.FoldersPaneView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Objects;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // initialising some useful variables
        String jdbcURL = "jdbc:sqlite:test.db";
        ObservableList<String> languages = FXCollections.observableArrayList();
        languages.setAll(Arrays.asList("c", "c++", "python", "java"));

        // database connection
        SQLiteConnectionManager sqLiteConnectionManager = new SQLiteConnectionManager();
        Connection connection = sqLiteConnectionManager.setConnection(jdbcURL);
        SQLiteDataAccessObject sqLiteDataAccessObject = new SQLiteDataAccessObject(connection, "snippetsTest", "foldersTest");
        sqLiteDataAccessObject.createSnippetsTable();



        CreateCodeSnippetViewModel createCodeSnippetViewModel = new CreateCodeSnippetViewModel(languages);
        CreateCodeSnippetPresenter presenter = new CreateCodeSnippetPresenter(createCodeSnippetViewModel);
        CreateCodeSnippetInteractor interactor = new CreateCodeSnippetInteractor(sqLiteDataAccessObject, presenter);
        CreateCodeSnippetController controller = new CreateCodeSnippetController(interactor);

        FoldersPaneViewModel foldersPaneViewModel = new FoldersPaneViewModel();

        // setting up scene
        CreateCodeSnippetView view = new CreateCodeSnippetView(controller, createCodeSnippetViewModel);
        FoldersPaneView foldersPaneView = new FoldersPaneView(foldersPaneViewModel);
        HBox hBox = new HBox(foldersPaneView.getRoot(), view.getRoot());
        HBox.setHgrow(view.getRoot(), Priority.ALWAYS);
        Scene scene = new Scene(hBox);
        foldersPaneView.getRoot().prefWidthProperty().bind(scene.widthProperty().multiply(0.1));
        primaryStage.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        view.getEditorNode().requestFocus();
        primaryStage.setTitle("Code Vault");
        primaryStage.show();
    }

}

