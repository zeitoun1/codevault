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
import com.zeitoun.codevault.folderspane.showfolders.interfaceadapter.ShowFoldersController;
import com.zeitoun.codevault.folderspane.showfolders.usecase.ShowFoldersInteractor;
import com.zeitoun.codevault.folderspane.showfolders.usecase.ShowFoldersOutPutBoundary;
import com.zeitoun.codevault.folderspane.showfolders.interfaceadapter.ShowFoldersPresenter;
import com.zeitoun.codevault.folderspane.view.FoldersPaneView;
import com.zeitoun.codevault.folderspane.view.FoldersPaneViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.sql.Connection;
import java.util.Arrays;


/**
 * Builds App's views, use cases and the main scene as well as loading any data that should be loaded at the start of
 * the application.
 */
public class AppBuilder {
    private final SQLiteConnectionManager sqLiteConnectionManager = new SQLiteConnectionManager();
    private SQLiteDataAccessObject sqLiteDataAccessObject;
    private CreateCodeSnippetViewModel createCodeSnippetViewModel;
    private CreateCodeSnippetView createCodeSnippetView;
    private FoldersPaneViewModel foldersPaneViewModel;
    private FoldersPaneView foldersPaneView;
    private CreateCodeSnippetController createCodeSnippetController;


    // Building DB parts

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


    // Building Views

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

    // Building UseCases

    public AppBuilder addCreateCodeSnippetUseCase() {
        CreateCodeSnippetOutputBoundary createCodeSnippetOutputBoundary = new CreateCodeSnippetPresenter(createCodeSnippetViewModel);
        CreateCodeSnippetInteractor createCodeSnippetInteractor = new CreateCodeSnippetInteractor(sqLiteDataAccessObject, createCodeSnippetOutputBoundary);

        createCodeSnippetController = new CreateCodeSnippetController(createCodeSnippetInteractor);
        createCodeSnippetView.setController(createCodeSnippetController);
        return this;
    }


    public AppBuilder addCreateFolderUseCase() {
        CreateFolderOutputBoundary createFolderOutputBoundary = new CreateFolderPresenter(foldersPaneViewModel);
        CreateFolderInteractor createFolderInteractor = new CreateFolderInteractor(sqLiteDataAccessObject, createFolderOutputBoundary);

        CreateFolderController createFolderController = new CreateFolderController(createFolderInteractor);
        foldersPaneView.setCreateFolderController(createFolderController);
        foldersPaneView.setCreateCodeSnippetController(createCodeSnippetController);
        return this;
    }


    public AppBuilder addShowFoldersUseCase() {
        ShowFoldersOutPutBoundary showFoldersOutPutBoundary = new ShowFoldersPresenter(foldersPaneViewModel);
        ShowFoldersInteractor showFoldersInteractor = new ShowFoldersInteractor(sqLiteDataAccessObject, showFoldersOutPutBoundary);

        ShowFoldersController showFoldersController = new ShowFoldersController(showFoldersInteractor);
        foldersPaneView.setShowFoldersController(showFoldersController);
        return this;
    }



    // Loading the folders

    public AppBuilder loadFolders() {

        foldersPaneView.getShowFoldersController().execute();
        return this;

    }



    // Building the Scene
    public Scene build() {
        HBox root = new HBox(foldersPaneView.getRoot(), createCodeSnippetView.getRoot());
        HBox.setHgrow(createCodeSnippetView.getRoot(), Priority.ALWAYS);
        Scene scene = new Scene(root);
        foldersPaneView.getRoot().prefWidthProperty().bind(scene.widthProperty().multiply(0.1));
        return scene;

    }





}
