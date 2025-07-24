package com.zeitoun.codevault.codesnippet.showsnippets.view;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SnippetsPaneViewModel {
    ObservableList<String> snippetsList = new SimpleListProperty<>(FXCollections.observableArrayList());

    public ObservableList<String> getSnippets() {
        return snippetsList;
    }

    public void setSnippetsList(List<String> snippetsList) {
        this.snippetsList.setAll(snippetsList);
    }
}
