package com.zeitoun.codevault.folder.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FoldersPaneViewModel {
    private final ObservableList<String> folders;
    private final StringProperty error;

    public FoldersPaneViewModel() {
        this.folders = FXCollections.observableArrayList();
        this.error = new SimpleStringProperty();
    }

    public ObservableList<String> getFolders() {
        return folders;
    }

    public String getError() {
        return error.getValue();
    }

    public void setError(String error) {
        this.error.setValue(error);
    }
}
