package com.zeitoun.codevault.snippetspane;

import com.zeitoun.codevault.codesnippet.enitity.CodeSnippet;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SnippetsPaneView {
    private final VBox root;
    private final ListView<String> snippetsPane;
    private final Label header;

    private final SnippetsPaneViewModel snippetsPaneViewModel;

    public SnippetsPaneView(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;

        snippetsPane = new ListView<>(this.snippetsPaneViewModel.getSnippets());
        header = new Label("Snippets");
        root = new VBox(header, snippetsPane);
    }

    public VBox getRoot() {
        return root;
    }
}
