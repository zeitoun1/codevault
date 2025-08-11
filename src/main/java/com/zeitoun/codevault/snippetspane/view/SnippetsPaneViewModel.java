package com.zeitoun.codevault.snippetspane.view;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SnippetsPaneViewModel {
    ObservableList<String> snippetsList = new SimpleListProperty<>(FXCollections.observableArrayList());
    StringProperty errorMessage = new SimpleStringProperty();

    public ObservableList<String> getSnippets() {
        return snippetsList;
    }

    public void setSnippetsList(List<String> snippetsList) {
        this.snippetsList.setAll(snippetsList);
    }

    public void setError(String error) {
        this.errorMessage.setValue(error);
    }

    public ObservableList<String> getSnippetsList() {
        return snippetsList;
    }
}
