package com.zeitoun.codevault.folder.view;

import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.folder.createfolder.interfaceadapter.CreateFolderController;
import com.zeitoun.codevault.folder.renamefolder.interfaceadapter.RenameFolderController;
import com.zeitoun.codevault.folder.showfolders.interfaceadapter.ShowFoldersController;
import com.zeitoun.codevault.shared.CustomListView;
import com.zeitoun.codevault.snippetspane.showsnippets.interfaceadapter.ShowSnippetsController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.Objects;

public class FoldersPaneView {
    private final VBox root;
    private final ListView<String> foldersPane;
    private final AnchorPane topBox;
    private final Label header;
    private final Button addButton;
    private final TextField nameBox;

    private final ContextMenu contextMenu;
    private final Button renameButton;
    private final Button deleteButton;

    private final FoldersPaneViewModel foldersPaneViewModel;

    private CreateFolderController createFolderController;
    private ShowFoldersController showFoldersController;
    private ShowSnippetsController showSnippetsController;
    private RenameFolderController renameFolderController;

    private final String name = "folders pane";

    private SceneManager sceneManager;


    public FoldersPaneView(FoldersPaneViewModel foldersPaneViewModel) {
        this.foldersPaneViewModel = new FoldersPaneViewModel();

        this.addButton = new Button();
        addButton.setId("add-button");
        Image addImg = new Image(Objects.requireNonNull(getClass().getResource("/add_button.png")).toExternalForm());
        ImageView addImageView = new ImageView(addImg);
        addImageView.setPreserveRatio(true);
        addImageView.setSmooth(true);
        addImageView.fitHeightProperty().bind(addButton.prefHeightProperty());
        addButton.setGraphic(addImageView);

        this.header = new Label("My Folders");
        this.topBox = new AnchorPane(header, addButton);
        AnchorPane.setRightAnchor(addButton, 10.0);

        this.foldersPane = new ListView<String>(foldersPaneViewModel.getFolders());
        Image folderIcon = new Image(Objects.requireNonNull(getClass().getResource("/light_theme_folder.png")).toExternalForm());
        foldersPane.setEditable(true);

        this.renameButton = new Button("rename");
        this.deleteButton = new Button("delete");
        this.contextMenu = new ContextMenu(new CustomMenuItem(renameButton), new CustomMenuItem(deleteButton));
        foldersPane.setContextMenu(contextMenu);

        foldersPane.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new CustomListView(folderIcon, new TextField());
            }
        });

        this.nameBox = new TextField();
        nameBox.setPromptText("Folder Name");
        nameBox.setVisible(false);
        this.root = new VBox(topBox, nameBox, foldersPane);

        addButton.setOnAction(new EventHandler<ActionEvent>() {
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

        // hide nameBox when user removes focus from nameBox
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

        foldersPane.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> stringEditEvent) {
                int editedIndex = stringEditEvent.getIndex();
                String newFolderName = stringEditEvent.getNewValue();
                renameFolderController.renameFolder(editedIndex, newFolderName);

            }
        });

        // on right-click on a folder, open context menu
        foldersPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isSecondaryButtonDown()) {
                    contextMenu.show(foldersPane, mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });

        // on click of rename button begin editing the focused cell
        renameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                foldersPane.edit(foldersPane.getSelectionModel().getSelectedIndex());
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

    public void setRenameFolderController(RenameFolderController renameFolderController) {
        this.renameFolderController = renameFolderController;
    }

    public void setShowSnippetsController(ShowSnippetsController showSnippetsController) {
        this.showSnippetsController = showSnippetsController;
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

    public String getName() {
        return name;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
