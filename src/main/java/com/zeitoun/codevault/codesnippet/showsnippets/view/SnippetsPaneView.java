package com.zeitoun.codevault.codesnippet.showsnippets.view;

import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.addSnippet.AddSnippetController;
import com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter.GetSnippetController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SnippetsPaneView {
    private final VBox root;
    private final ListView<String> snippetsPane;
    private final Label header;
    private final HBox topBox;
    private final Button addBUtton;
    private final TextField nameBox;

    private final SnippetsPaneViewModel snippetsPaneViewModel;
    private GetSnippetController getSnippetController;
    private AddSnippetController addSnippetController;
    private SceneManager sceneManager;

    private final String name = "snippets pane";

    public SnippetsPaneView(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;
        header = new Label("Snippets");
        addBUtton = new Button("Add snippet");
        topBox = new HBox(header, addBUtton);

        nameBox = new TextField();
        nameBox.setPromptText("Snippet Name");
        nameBox.setVisible(false);

        snippetsPane = new ListView<>(this.snippetsPaneViewModel.getSnippets());

        root = new VBox(topBox, nameBox, snippetsPane);

        snippetsPane.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                getSnippetController.getSnippet(snippetsPane.getSelectionModel().getSelectedItem());
            }
        });

        nameBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                    addSnippetController.addSnippet(nameBox.getText());
                    nameBox.clear();
                    nameBox.setVisible(false);
                }

            }
        });

        // show nameBox when addButton is clicked
        addBUtton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nameBox.setVisible(true);
            }
        });

        // hide nameBox when user removes focus from nameBox
        nameBox.focusedProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                nameBox.setVisible(nameBox.isFocused());
            }
        });
    }

    public VBox getRoot() {
        return root;
    }


    public void setGetSnippetController(GetSnippetController getSnippetController) {
        this.getSnippetController = getSnippetController;
    }

    public void setAddSnippetController(AddSnippetController addSnippetController) {
        this.addSnippetController = addSnippetController;
    }

    public String getName() {
        return name;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
