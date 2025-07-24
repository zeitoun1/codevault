package com.zeitoun.codevault.folder.view;

import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetController;
import com.zeitoun.codevault.folder.createfolder.interfaceadapter.CreateFolderController;
import com.zeitoun.codevault.folder.showfolders.interfaceadapter.ShowFoldersController;
import com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter.ShowSnippetsController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FoldersPaneView {
    private final VBox root;
    private final ListView<String> foldersPane;
    private final HBox topBox;
    private final Label header;
    private final Button addBUtton;
    private final TextField nameBox;

    private final FoldersPaneViewModel foldersPaneViewModel;

    private ShowFoldersController showFoldersController;
    private ShowSnippetsController showSnippetsController;

    private final String name = "folders pane";

    private SceneManager sceneManager;

    private CreateFolderController createFolderController;

    public FoldersPaneView(FoldersPaneViewModel foldersPaneViewModel) {
        this.foldersPaneViewModel = new FoldersPaneViewModel();
        this.addBUtton = new Button("new folder");
        this.header = new Label("My Folders");
        this.topBox = new HBox(header, addBUtton);
        this.foldersPane = new ListView<String>(foldersPaneViewModel.getFolders());
        this.nameBox = new TextField();
        nameBox.setPromptText("Folder Name");
        nameBox.setVisible(false);
        this.root = new VBox(topBox, nameBox, foldersPane);

        addBUtton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nameBox.setVisible(true);
            }
        });

        nameBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    createFolderController.execute(nameBox.getText());
                    nameBox.clear();
                    nameBox.setVisible(false);
                }

            }
        });

        // close nameBox when user removes focus from nameBox
        nameBox.focusedProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                nameBox.setVisible(nameBox.isFocused());
            }
        });


        // on selection of a folder, show the snippets
        foldersPane.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                showSnippetsController.showSnippets(foldersPane.getSelectionModel().getSelectedItem());
                sceneManager.addNodeToRoot("snippets pane", 1);
            }
        });

    }

    public VBox getRoot() {
        return root;
    }
    public void setCreateFolderController(CreateFolderController createFolderController) {
        this.createFolderController = createFolderController;
    }

    public void setShowFoldersController(ShowFoldersController showFoldersController) {
        this.showFoldersController = showFoldersController;
    }

    public FoldersPaneViewModel getFoldersPaneViewModel() {
        return foldersPaneViewModel;
    }

    public ShowFoldersController getShowFoldersController() {
        return showFoldersController;
    }

    public ShowSnippetsController getShowSnippetsController() {
        return showSnippetsController;
    }

    public void setShowSnippetsController(ShowSnippetsController showSnippetsController) {
        this.showSnippetsController = showSnippetsController;
    }

    public String getName() {
        return name;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
