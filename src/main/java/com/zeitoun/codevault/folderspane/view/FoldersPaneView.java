package com.zeitoun.codevault.folderspane.view;

import com.zeitoun.codevault.folderspane.createfolder.CreateFolderController;
import com.zeitoun.codevault.folderspane.interfaceadapter.FoldersPaneViewModel;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class FoldersPaneView {
    private final VBox root;
    private final ListView foldersPane;
    private final HBox topBox;
    private final Label header;
    private final Button addBUtton;
    private final FoldersPaneViewModel foldersPaneViewModel;
    private CreateFolderController createFolderController;


    public FoldersPaneView(FoldersPaneViewModel foldersPaneViewModel) {
        this.foldersPaneViewModel = new FoldersPaneViewModel();
        this.addBUtton = new Button("new folder");
        this.header = new Label("My Folders");
        this.topBox = new HBox(header, addBUtton);
        this.foldersPane = new ListView<String>(foldersPaneViewModel.getFolders());
        this.root = new VBox(topBox, foldersPane);
    }

    public VBox getRoot() {
        return root;
    }

}
