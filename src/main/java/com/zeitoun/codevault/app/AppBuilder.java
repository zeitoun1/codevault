package com.zeitoun.codevault.app;

import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetController;
import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetPresenter;
import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetInteractor;
import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetOutputBoundary;
import com.zeitoun.codevault.codesnippet.view.CodeSnippetView;
import com.zeitoun.codevault.codesnippet.view.CreateCodeSnippetViewModel;
import com.zeitoun.codevault.codesnippet.view.GetSnippetViewModel;
import com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter.GetSnippetController;
import com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter.GetSnippetPresenter;
import com.zeitoun.codevault.codesnippet.getsnippet.usecase.GetSnippetInteractor;
import com.zeitoun.codevault.codesnippet.getsnippet.usecase.GetSnippetOutputBoundary;
import com.zeitoun.codevault.database.SQLiteConnectionManager;
import com.zeitoun.codevault.database.SQLiteDataAccessObject;
import com.zeitoun.codevault.folder.createfolder.interfaceadapter.CreateFolderController;
import com.zeitoun.codevault.folder.createfolder.interfaceadapter.CreateFolderPresenter;
import com.zeitoun.codevault.folder.createfolder.usecase.CreateFolderInteractor;
import com.zeitoun.codevault.folder.createfolder.usecase.CreateFolderOutputBoundary;
import com.zeitoun.codevault.folder.showfolders.interfaceadapter.ShowFoldersController;
import com.zeitoun.codevault.folder.showfolders.usecase.ShowFoldersInteractor;
import com.zeitoun.codevault.folder.showfolders.usecase.ShowFoldersOutPutBoundary;
import com.zeitoun.codevault.folder.showfolders.interfaceadapter.ShowFoldersPresenter;
import com.zeitoun.codevault.folder.view.FoldersPaneView;
import com.zeitoun.codevault.folder.view.FoldersPaneViewModel;
import com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter.ShowSnippetsController;
import com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter.ShowSnippetsPresenter;
import com.zeitoun.codevault.codesnippet.showsnippets.usecase.ShowSnippetsInteractor;
import com.zeitoun.codevault.codesnippet.showsnippets.usecase.ShowSnippetsOutputBoundary;
import com.zeitoun.codevault.codesnippet.view.SnippetsPaneViewModel;
import com.zeitoun.codevault.shared.AppContext;
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
    private GetSnippetViewModel getSnippetViewModel;
    private CodeSnippetView codeSnippetView;
    private FoldersPaneViewModel foldersPaneViewModel;
    private FoldersPaneView foldersPaneView;
    private SnippetsPaneViewModel snippetsPaneViewModel;
    private final SceneManager sceneManager = new SceneManager();
    private final AppContext appContext = new AppContext();

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
        getSnippetViewModel = new GetSnippetViewModel();
        snippetsPaneViewModel = new SnippetsPaneViewModel();
        codeSnippetView = new CodeSnippetView(createCodeSnippetViewModel, getSnippetViewModel, snippetsPaneViewModel);
        codeSnippetView.setSceneManager(sceneManager);
        sceneManager.addNode(codeSnippetView.getName(), codeSnippetView.getRoot());
        return this;
    }


    public AppBuilder addFoldersPaneView() {
        foldersPaneViewModel = new FoldersPaneViewModel();
        foldersPaneView = new FoldersPaneView(foldersPaneViewModel);
        foldersPaneView.setSceneManager(sceneManager);
        sceneManager.addNode(foldersPaneView.getName(), foldersPaneView.getRoot());
        return this;
    }


    // Building UseCases

    public AppBuilder addCreateCodeSnippetUseCase() {
        CreateCodeSnippetOutputBoundary createCodeSnippetOutputBoundary = new CreateCodeSnippetPresenter(createCodeSnippetViewModel);
        CreateCodeSnippetInteractor createCodeSnippetInteractor = new CreateCodeSnippetInteractor(sqLiteDataAccessObject, createCodeSnippetOutputBoundary);

        CreateCodeSnippetController createCodeSnippetController = new CreateCodeSnippetController(createCodeSnippetInteractor, appContext);
        codeSnippetView.setCreateCodeSnippetController(createCodeSnippetController);
        return this;
    }


    public AppBuilder addCreateFolderUseCase() {
        CreateFolderOutputBoundary createFolderOutputBoundary = new CreateFolderPresenter(foldersPaneViewModel);
        CreateFolderInteractor createFolderInteractor = new CreateFolderInteractor(sqLiteDataAccessObject, createFolderOutputBoundary);

        CreateFolderController createFolderController = new CreateFolderController(createFolderInteractor);
        foldersPaneView.setCreateFolderController(createFolderController);
        return this;
    }


    public AppBuilder addShowFoldersUseCase() {
        ShowFoldersOutPutBoundary showFoldersOutPutBoundary = new ShowFoldersPresenter(foldersPaneViewModel);
        ShowFoldersInteractor showFoldersInteractor = new ShowFoldersInteractor(sqLiteDataAccessObject, showFoldersOutPutBoundary);

        ShowFoldersController showFoldersController = new ShowFoldersController(showFoldersInteractor);
        foldersPaneView.setShowFoldersController(showFoldersController);
        foldersPaneView.setShowSnippetsController(codeSnippetView.getShowSnippetsController());
        return this;
    }

    public AppBuilder addShowSnippetsUseCase() {
        ShowSnippetsOutputBoundary showSnippetsOutputBoundary = new ShowSnippetsPresenter(snippetsPaneViewModel);
        ShowSnippetsInteractor showSnippetsInteractor = new ShowSnippetsInteractor(sqLiteDataAccessObject, showSnippetsOutputBoundary, appContext);

        ShowSnippetsController showSnippetsController = new ShowSnippetsController(showSnippetsInteractor, appContext);
        codeSnippetView.setShowSnippetsController(showSnippetsController);
        return this;
    }

    public AppBuilder addGetSnippetUseCase() {
        GetSnippetOutputBoundary getSnippetOutputBoundary = new GetSnippetPresenter(getSnippetViewModel);
        GetSnippetInteractor getSnippetInteractor = new GetSnippetInteractor(sqLiteDataAccessObject, getSnippetOutputBoundary);

        GetSnippetController getSnippetController = new GetSnippetController(getSnippetInteractor, appContext);
        codeSnippetView.setGetSnippetController(getSnippetController);
        return this;
    }



    // Loading the folders

    public AppBuilder loadFolders() {
        foldersPaneView.getShowFoldersController().execute();
        return this;

    }



    // Building the Scene
    public Scene build() {
        HBox root = new HBox(foldersPaneView.getRoot(), codeSnippetView.getRoot());
        HBox.setHgrow(codeSnippetView.getRoot(), Priority.ALWAYS);
        Scene scene = new Scene(root);
        foldersPaneView.getRoot().prefWidthProperty().bind(scene.widthProperty().multiply(0.1));
        sceneManager.setCurrentScene(scene);
        return scene;

    }

}
