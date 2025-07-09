package com.zeitoun.codevault.codesnippet.showsnippets.view;

import com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter.ShowSnippetsController;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SnippetsPaneView {
    private final VBox root;
    private final ListView<String> snippetsPane;
    private final Label header;

    private final SnippetsPaneViewModel snippetsPaneViewModel;
    private ShowSnippetsController showSnippetsController;

    public SnippetsPaneView(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;

        snippetsPane = new ListView<>(this.snippetsPaneViewModel.getSnippets());
        header = new Label("Snippets");
        root = new VBox(header, snippetsPane);
    }

    public VBox getRoot() {
        return root;
    }

    public ShowSnippetsController getShowSnippetsController() {
        return showSnippetsController;
    }

    public void setShowSnippetsController(ShowSnippetsController showSnippetsController) {
        this.showSnippetsController = showSnippetsController;
    }
}
