package com.zeitoun.codevault.app;

import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetPresenter;
import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetController;
import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetInteractor;
import com.zeitoun.codevault.codesnippet.createsnippet.view.CreateCodeSnippetView;
import com.zeitoun.codevault.codesnippet.createsnippet.view.CreateCodeSnippetViewModel;
import com.zeitoun.codevault.database.SQLiteConnectionManager;
import com.zeitoun.codevault.database.SQLiteDataAccessObject;
import com.zeitoun.codevault.folderspane.createfolder.interfaceadapter.CreateFolderController;
import com.zeitoun.codevault.folderspane.createfolder.usecase.CreateFolderInteractor;
import com.zeitoun.codevault.folderspane.createfolder.interfaceadapter.CreateFolderPresenter;
import com.zeitoun.codevault.folderspane.view.FoldersPaneViewModel;
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

