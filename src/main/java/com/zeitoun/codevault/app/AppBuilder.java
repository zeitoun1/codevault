package com.zeitoun.codevault.app;

import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetController;
import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetPresenter;
import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetInteractor;
import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetOutputBoundary;
import com.zeitoun.codevault.codesnippet.createsnippet.view.CreateCodeSnippetView;
import com.zeitoun.codevault.codesnippet.createsnippet.view.CreateCodeSnippetViewModel;
import com.zeitoun.codevault.database.SQLiteConnectionManager;
import com.zeitoun.codevault.database.SQLiteDataAccessObject;
import com.zeitoun.codevault.folderspane.createfolder.interfaceadapter.CreateFolderController;
import com.zeitoun.codevault.folderspane.createfolder.interfaceadapter.CreateFolderPresenter;
import com.zeitoun.codevault.folderspane.createfolder.usecase.CreateFolderInteractor;
import com.zeitoun.codevault.folderspane.createfolder.usecase.CreateFolderOutputBoundary;
import com.zeitoun.codevault.folderspane.view.FoldersPaneView;
import com.zeitoun.codevault.folderspane.view.FoldersPaneViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.sql.Connection;
import java.util.Arrays;

public class AppBuilder {
    private final SQLiteConnectionManager sqLiteConnectionManager = new SQLiteConnectionManager();
    private SQLiteDataAccessObject sqLiteDataAccessObject;
    private CreateCodeSnippetViewModel createCodeSnippetViewModel;
    private CreateCodeSnippetView createCodeSnippetView;
    private FoldersPaneViewModel foldersPaneViewModel;
    private FoldersPaneView foldersPaneView;

    public AppBuilder connectToDB(String jdbcURL, String snippetsTable, String FoldersTable) {
        Connection connection = sqLiteConnectionManager.setConnection(jdbcURL);
        sqLiteDataAccessObject = new SQLiteDataAccessObject(connection, snippetsTable, FoldersTable);
        return this;
    }


    public AppBuilder createTables() {
        sqLiteDataAccessObject.createSnippetsTable();
        sqLiteDataAccessObject.createFoldersTable();
        return this;
    }

    public AppBuilder addCreateCodeSnippetView() {
        ObservableList<String> languages = FXCollections.observableArrayList();
        languages.setAll(Arrays.asList("c", "c++", "python", "java"));
        createCodeSnippetViewModel = new CreateCodeSnippetViewModel(languages);
        createCodeSnippetView = new CreateCodeSnippetView(createCodeSnippetViewModel);
        return this;
    }


    public AppBuilder addFoldersPaneView() {
        foldersPaneViewModel = new FoldersPaneViewModel();
        foldersPaneView = new FoldersPaneView(foldersPaneViewModel);
        return this;
    }

    public AppBuilder addCreateCodeSnippetUseCase() {
        CreateCodeSnippetOutputBoundary createCodeSnippetOutputBoundary = new CreateCodeSnippetPresenter(createCodeSnippetViewModel);
        CreateCodeSnippetInteractor createCodeSnippetInteractor = new CreateCodeSnippetInteractor(sqLiteDataAccessObject, createCodeSnippetOutputBoundary);

        CreateCodeSnippetController createCodeSnippetController = new CreateCodeSnippetController(createCodeSnippetInteractor);
        createCodeSnippetView.setController(createCodeSnippetController);
        return this;
    }


    public AppBuilder addCreateFolderUseCase() {
        CreateFolderOutputBoundary createFolderOutputBoundary = new CreateFolderPresenter(foldersPaneViewModel);
        CreateFolderInteractor createFolderInteractor = new CreateFolderInteractor(sqLiteDataAccessObject, createFolderOutputBoundary);

        CreateFolderController createFolderController = new CreateFolderController(createFolderInteractor);
        foldersPaneView.setCreateFolderController(createFolderController);
        return this;
    }

    public Scene build() {
        HBox root = new HBox(foldersPaneView.getRoot(), createCodeSnippetView.getRoot());
        HBox.setHgrow(createCodeSnippetView.getRoot(), Priority.ALWAYS);
        Scene scene = new Scene(root);
        foldersPaneView.getRoot().prefWidthProperty().bind(scene.widthProperty().multiply(0.1));
        return scene;

    }




}
