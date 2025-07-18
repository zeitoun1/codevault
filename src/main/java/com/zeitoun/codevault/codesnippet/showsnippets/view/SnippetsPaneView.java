package com.zeitoun.codevault.codesnippet.showsnippets.view;

import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter.GetSnippetController;
import com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter.ShowSnippetsController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SnippetsPaneView {
    private final VBox root;
    private final ListView<String> snippetsPane;
    private final HBox header;
    private final Label label;
    private final Button addButton;

    private final SnippetsPaneViewModel snippetsPaneViewModel;
    private ShowSnippetsController showSnippetsController;


    private GetSnippetController getSnippetController;

    private SceneManager sceneManager;

    private final String name = "snippets pane";

    public SnippetsPaneView(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;
        snippetsPane = new ListView<>(this.snippetsPaneViewModel.getSnippets());
        addButton = new Button("add snippet");
        label = new Label("snippets");
        header = new HBox(label, addButton);
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

    public void setGetSnippetController(GetSnippetController getSnippetController) {
        this.getSnippetController = getSnippetController;
    }
}
