package com.zeitoun.codevault.snippetspane.view;

import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter.GetSnippetController;
import com.zeitoun.codevault.shared.CustomListView;
import com.zeitoun.codevault.snippetspane.addsnippet.interfaceadapter.AddSnippetController;
import com.zeitoun.codevault.snippetspane.deletesnippet.interfaceadapter.DeleteSnippetController;
import com.zeitoun.codevault.snippetspane.renamesnippet.interfaceadapter.RenameSnippetController;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.Objects;
import java.util.function.Consumer;

public class SnippetsPaneView {
    private final VBox root;
    private final ListView<String> snippetsPane;
    private final Label header;
    private final AnchorPane topBox;
    private final Button addButton;
    private final TextField nameBox;

    private final ContextMenu contextMenu;
    private final Button renameButton;

    private final Button deleteButton;
    private final ButtonType deleteConfirmationButton;
    private final ButtonType cancelButton;

    private final SnippetsPaneViewModel snippetsPaneViewModel;

    private GetSnippetController getSnippetController;
    private AddSnippetController addSnippetController;

    private DeleteSnippetController deleteSnippetController;
    private RenameSnippetController renameSnippetController;

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
        addImageView.setSmooth(true);
        addImageView.fitHeightProperty().bind(addButton.prefHeightProperty());
        addButton.setGraphic(addImageView);

        topBox = new AnchorPane(header, addButton);
        AnchorPane.setRightAnchor(addButton, 10.0);
        nameBox = new TextField();
        nameBox.setPromptText("Snippet Name");
        nameBox.setVisible(false);


        Image codeIcon = new Image(Objects.requireNonNull(getClass().getResource("/light_theme_code_snippet.png")).toExternalForm());
        snippetsPane = new ListView<>(this.snippetsPaneViewModel.getSnippets());
        snippetsPane.setEditable(true);

        snippetsPane.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new CustomListView(codeIcon, new TextField());
            }
        });

        this.renameButton = new Button("rename");
        this.deleteButton = new Button("delete");
        this.deleteConfirmationButton = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        this.cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        this.contextMenu = new ContextMenu(new CustomMenuItem(renameButton), new CustomMenuItem(deleteButton));
        snippetsPane.setContextMenu(contextMenu);


        root = new VBox(topBox, nameBox, snippetsPane);


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

        // hide nameBox when user removes focus from nameBox
        nameBox.focusedProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                nameBox.setVisible(nameBox.isFocused());
            }
        });

        // show nameBox when addButton is clicked
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                nameBox.setVisible(true);
                nameBox.requestFocus();
            }
        });


        snippetsPane.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                getSnippetController.getSnippet(snippetsPane.getSelectionModel().getSelectedItem());
            }
        });

        // on right-click on a folder, open context menu
        snippetsPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.isSecondaryButtonDown()) {
                    contextMenu.show(snippetsPane, mouseEvent.getX(), mouseEvent.getY());
                }
            }
        });

        snippetsPane.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> stringEditEvent) {
                int editedIndex = stringEditEvent.getIndex();
                String newFolderName = stringEditEvent.getNewValue();
                renameSnippetController.renameSnippet(editedIndex, newFolderName);
            }
        });

        // on click of rename button begin editing the focused cell
        renameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                snippetsPane.edit(snippetsPane.getSelectionModel().getSelectedIndex());
            }
        });

        // on click of delete button delete the selected snippet
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this code snippet", cancelButton, deleteConfirmationButton);
                confirmation.setHeaderText("Delete");
                confirmation.setResizable(false);
                confirmation.showAndWait().ifPresent(buttonType -> {
                    if(buttonType == deleteConfirmationButton) {
                        deleteSnippetController.deleteSnippet(snippetsPane.getSelectionModel().getSelectedIndex());
                    } else {
                        confirmation.close();
                    }
                });
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

    public void setDeleteSnippetController(DeleteSnippetController deleteSnippetController) {
        this.deleteSnippetController = deleteSnippetController;
    }

    public String getName() {
        return name;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setRenameSnippetController(RenameSnippetController renameSnippetController) {
        this.renameSnippetController = renameSnippetController;
    }
}
