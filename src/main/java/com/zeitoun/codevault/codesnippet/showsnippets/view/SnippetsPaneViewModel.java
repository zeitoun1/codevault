package com.zeitoun.codevault.codesnippet.showsnippets.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SnippetsPaneViewModel {
    private ObservableList<String> snippets;

    public SnippetsPaneViewModel() {
        this.snippets = FXCollections.observableArrayList();

    }

    public ObservableList<String> getSnippets() {
        return snippets;
    }


}
