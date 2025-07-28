package com.zeitoun.codevault.codesnippet.showsnippets.view;

import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.addSnippet.AddSnippetController;
import com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter.GetSnippetController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.Objects;

public class SnippetsPaneView {
    private final VBox root;
    private final ListView<String> snippetsPane;
    private final Label header;
    private final AnchorPane topBox;
    private final Button addButton;
    private final TextField nameBox;

    private final SnippetsPaneViewModel snippetsPaneViewModel;
    private GetSnippetController getSnippetController;
    private AddSnippetController addSnippetController;
    private SceneManager sceneManager;

    private final String name = "snippets pane";

    public SnippetsPaneView(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;
        header = new Label("Snippets");

        addButton = new Button();
        addButton.setId("add-button");
        Image addImg = new Image(Objects.requireNonNull(getClass().getResource("/add_button.png")).toExternalForm());
        ImageView addImageView = new ImageView(addImg);
        addImageView.setPreserveRatio(true);
        addImageView.fitHeightProperty().bind(addButton.prefHeightProperty());
        addButton.setGraphic(addImageView);

        topBox = new AnchorPane(header, addButton);
        AnchorPane.setRightAnchor(addButton, 10.0);
        nameBox = new TextField();
        nameBox.setPromptText("Snippet Name");
        nameBox.setVisible(false);

        Image code_icon = new Image(Objects.requireNonNull(getClass().getResource("/light_theme_code_snippet.png")).toExternalForm());
        snippetsPane = new ListView<>(this.snippetsPaneViewModel.getSnippets());
        snippetsPane.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            final ImageView imageView = new ImageView(code_icon);
                            imageView.setPreserveRatio(true);
                            imageView.fitHeightProperty().bind(heightProperty().multiply(0.55));
                            setText(item);
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });

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
        addButton.setOnAction(new EventHandler<ActionEvent>() {
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
