package com.zeitoun.codevault.folderspane.interfaceadapter;

import com.zeitoun.codevault.folderspane.view.FoldersPaneView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class FoldersPaneViewModel {
    private final ObservableList<String> folders;

    public FoldersPaneViewModel() {
        this.folders = FXCollections.observableArrayList();
    }

    public ObservableList<String> getFolders() {
        return folders;
    }
}
