package com.zeitoun.codevault.folderspane.view;

import com.zeitoun.codevault.folderspane.createfolder.CreateFolderController;
import com.zeitoun.codevault.folderspane.interfaceadapter.FoldersPaneViewModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class FoldersPaneView {
    private final VBox root;
    private final ListView foldersPane;
    private final HBox topBox;
    private final Label header;
    private final Button addBUtton;
    private final TextField nameBox;

    private final FoldersPaneViewModel foldersPaneViewModel;

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


    }

    public VBox getRoot() {
        return root;
    }

    public void setCreateFolderController(CreateFolderController createFolderController) {
        this.createFolderController = createFolderController;
    }

    public FoldersPaneViewModel getFoldersPaneViewModel() {
        return foldersPaneViewModel;
    }
}
