package com.zeitoun.codevault.codesnippet.showsnippets.view;

import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter.GetSnippetController;
import com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter.ShowSnippetsController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SnippetsPaneView {
    private final VBox root;
    private final ListView<String> snippetsPane;
    private final Label header;

    private final SnippetsPaneViewModel snippetsPaneViewModel;
    private GetSnippetController getSnippetController;

    private SceneManager sceneManager;

    private final String name = "snippets pane";

    public SnippetsPaneView(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;
        snippetsPane = new ListView<>(this.snippetsPaneViewModel.getSnippets());
        header = new Label("Snippets");
        root = new VBox(header, snippetsPane);

        snippetsPane.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                getSnippetController.getSnippet(snippetsPane.getSelectionModel().getSelectedItem());
            }
        });
    }

    public VBox getRoot() {
        return root;
    }


    public void setGetSnippetController(GetSnippetController getSnippetController) {
        this.getSnippetController = getSnippetController;
    }

    public String getName() {
        return name;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
