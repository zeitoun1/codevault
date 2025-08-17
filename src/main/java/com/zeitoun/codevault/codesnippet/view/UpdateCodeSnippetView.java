package com.zeitoun.codevault.codesnippet.view;

import com.zeitoun.codevault.ToastNotification;
import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.updatesnippet.interfaceadapter.UpdateCodeSnippetController;
import eu.mihosoft.monacofx.MonacoFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.Objects;


public class UpdateCodeSnippetView {
    private final VBox root;
    private final MonacoFX editorNode;
    private final StackPane stackPane;

    private final AnchorPane topNode;
    private final Button saveButton;
    private final Button copyButton;
    private final ComboBox<String> languageBox;
    private final Label languageLabel;
    private final Label snippetLabel;



    private final HBox bottomNode;
    private final TextArea descriptionBox;

    private UpdateCodeSnippetController updateCodeSnippetController;

    private final UpdateCodeSnippetViewModel updateCodeSnippetViewModel;
    private final GetSnippetViewModel getSnippetViewModel;


    private SceneManager sceneManager;
    private final String name = "create snippet";

    public UpdateCodeSnippetView(UpdateCodeSnippetViewModel updateCodeSnippetViewModel, GetSnippetViewModel getSnippetViewModel) {
        this.updateCodeSnippetViewModel = updateCodeSnippetViewModel;
        this.getSnippetViewModel = getSnippetViewModel;
        // initializing snippetNode (where snippet creations takes place and where the contents of snippets are viewed)



        // stackPane
        editorNode = new MonacoFX();
        editorNode.getEditor().setCurrentTheme("vs-dark");
        stackPane = new StackPane(editorNode);
        VBox.setVgrow(stackPane, Priority.ALWAYS);

        // top node
        languageBox = new ComboBox<>(updateCodeSnippetViewModel.getEditorLanguages());
        languageLabel = new Label("Language:");
        saveButton = new Button("Save");
        saveButton.setFont(new Font(18.0));
        copyButton = new Button();
        Image addImg = new Image(Objects.requireNonNull(getClass().getResource("/copy_to_clipboard.png")).toExternalForm());
        ImageView addImageView = new ImageView(addImg);
        addImageView.setPreserveRatio(true);
        addImageView.setSmooth(true);
        addImageView.fitHeightProperty().bind(copyButton.prefHeightProperty());
        copyButton.setGraphic(addImageView);
        copyButton.setPrefHeight(24);
        snippetLabel = new Label();
        snippetLabel.textProperty().bind(getSnippetViewModel.nameProperty());

        HBox languageContainer = new HBox(languageLabel, languageBox);
        languageContainer.setSpacing(5.0);

        HBox buttonsContainer = new HBox(saveButton, copyButton);
        buttonsContainer.setSpacing(10.0);

        HBox rightContainer = new HBox(languageContainer, buttonsContainer);
        rightContainer.setSpacing(10.0);

        topNode = new AnchorPane(snippetLabel, rightContainer);
        AnchorPane.setLeftAnchor(snippetLabel, 10.0);
        AnchorPane.setRightAnchor(rightContainer, 2.0);


        // bottom node

        descriptionBox = new TextArea();
        descriptionBox.setPromptText("Description");
        descriptionBox.setFont(new Font(18));


        bottomNode = new HBox(descriptionBox);

        root = new VBox(topNode, stackPane, bottomNode);


        // selecting language in editor when creating a snippet
        languageBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                editorNode.getEditor().setCurrentLanguage(languageBox.getValue());
            }
        });

        // adding a listener to saveButton to start the CreateCodeSnippet use case upon click
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateCodeSnippetController.execute(editorNode.getEditor().getDocument().getText(), descriptionBox.getText(), languageBox.getValue());

            }
        });

        // copying code snippet to clipboard
        copyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(editorNode.getEditor().getDocument().getText());
                clipboard.setContent(content);

                ToastNotification notification = new ToastNotification(stackPane);
                notification.setText("Copied to clipboard");
                notification.showAndHide(1000);
            }
        });

        // showing error message when CreateCodeSnippet is unsuccessful
        this.updateCodeSnippetViewModel.errorMessageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                if(!newVal.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(updateCodeSnippetViewModel.errorMessageProperty().getValue()); // revalidates error message
                    updateCodeSnippetViewModel.errorMessageProperty().setValue("");
                    alert.show();
                }

            }
        });

        // showing success message when CreateCodeSnippet use case is successful
        this.updateCodeSnippetViewModel.successMessageProperty().addListener((observableValue, oldVal, newVal) -> {
            if(!newVal.isEmpty()){
                ToastNotification notification = new ToastNotification(stackPane);
                notification.setText(updateCodeSnippetViewModel.successMessageProperty().getValue());
                updateCodeSnippetViewModel.successMessageProperty().setValue("");
                notification.showAndHide(1000);
            }

        });

        // Updating the UI elements in the snippetNode to get the selected snippet
        this.getSnippetViewModel.nameProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                editorNode.getEditor().getDocument().setText(getSnippetViewModel.getCode());
                descriptionBox.setText(getSnippetViewModel.getDescription());
                languageBox.setValue(getSnippetViewModel.getLanguage());
            }
        });


    }

    public VBox getRoot() {
        return root;
    }

    public void setUpdateCodeSnippetController(UpdateCodeSnippetController updateCodeSnippetController) {
        this.updateCodeSnippetController = updateCodeSnippetController;
    }

    public String getName() {
        return name;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

}