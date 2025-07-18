package com.zeitoun.codevault.codesnippet.showsnippets.view;

import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter.ShowSnippetsController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SnippetsPaneView {
    private final VBox root;
    private final ListView<String> snippetsPane;
    private final Label header;

    private final SnippetsPaneViewModel snippetsPaneViewModel;
    private ShowSnippetsController showSnippetsController;

    private SceneManager sceneManager;

    private final String name = "snippets pane";

    public SnippetsPaneView(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;
        snippetsPane = new ListView<>(this.snippetsPaneViewModel.getSnippets());
        header = new Label("Snippets");
        root = new VBox(header, snippetsPane);
        root.setVisible(false);


        // when the snippets list is set (show snippets use case), show the snippets pane view
        snippetsPaneViewModel.getSnippets().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                root.setVisible(true);
            }
        });
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

    public String getName() {
        return name;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
