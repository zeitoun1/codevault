package com.zeitoun.codevault.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // initialising some app variables
        String jdbcURL = "jdbc:sqlite:test.db";
        String snippetsTable = "snippets";
        String foldersTable = "folders";

        AppBuilder appBuilder = new AppBuilder();
        Scene scene = appBuilder
                .connectToDB(jdbcURL, snippetsTable, foldersTable)
                .createTables()
                .addCreateCodeSnippetView()
                .addFoldersPaneView()
                .addShowSnippetsUseCase()
                .addCreateCodeSnippetUseCase()
                .addCreateFolderUseCase()
                .addShowFoldersUseCase()
                .loadFolders()
                .build();


        primaryStage.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        primaryStage.setTitle("Code Vault");
        primaryStage.show();
    }

}

