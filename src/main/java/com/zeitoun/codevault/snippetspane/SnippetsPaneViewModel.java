package com.zeitoun.codevault.snippetspane;

import com.zeitoun.codevault.codesnippet.enitity.CodeSnippet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SnippetsPaneViewModel {
    private ObservableList<String> snippets;

    public SnippetsPaneViewModel() {
        this.snippets = FXCollections.observableArrayList();

    }

    public ObservableList<String> getSnippets() {
        return snippets;
    }


}
