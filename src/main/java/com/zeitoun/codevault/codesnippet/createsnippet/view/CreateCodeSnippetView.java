package com.zeitoun.codevault.codesnippet.createsnippet.view;

import com.zeitoun.codevault.ToastNotification;
import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetController;
import eu.mihosoft.monacofx.MonacoFX;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class CreateCodeSnippetView {
    private final VBox root;
    private final AnchorPane topNode;
    private final TextField nameBox;
    private final ComboBox<String> languageBox;
    private final StackPane stackPane;



    private final MonacoFX editorNode;
    private final HBox bottomNode;
    private final TextArea descriptionBox;
    private final Button saveButton;



    private  CreateCodeSnippetController controller;
    private final CreateCodeSnippetViewModel viewModel;
    private ToastNotification toastNotification;


    public CreateCodeSnippetView(CreateCodeSnippetViewModel viewModel) {
        this.viewModel = viewModel;


        // initializing child nodes
        this.nameBox = new TextField();
        nameBox.setPrefWidth(1000);
        nameBox.setPromptText("Snippet name");
        nameBox.setFont(new Font(20));


        this.languageBox = new ComboBox<>(viewModel.getEditorLanguages());

        this.editorNode = new MonacoFX();
        editorNode.getEditor().setCurrentTheme("vs-dark");

        languageBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                editorNode.getEditor().setCurrentLanguage(languageBox.getValue());
            }
        });


        this.descriptionBox = new TextArea();
        descriptionBox.setPromptText("Description");
        descriptionBox.setFont(new Font(18));


        this.saveButton = new Button("Save");
        saveButton.setFont(new Font(18.0));


        // initializing parent nodes and scene
        AnchorPane.setRightAnchor(languageBox, 20.0);
        AnchorPane.setTopAnchor(languageBox, 2.0);
        this.topNode = new AnchorPane(this.nameBox, this.languageBox);



        this.stackPane = new StackPane(this.editorNode);
        VBox.setVgrow(stackPane, Priority.ALWAYS);

        this.toastNotification = new ToastNotification(stackPane);
        toastNotification.setVisible(false);

        this.bottomNode = new HBox(this.descriptionBox, this.saveButton);


        this.root = new VBox(this.topNode, this.stackPane, this.bottomNode);




        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.execute(editorNode.getEditor().getDocument().getText(), nameBox.getText(), descriptionBox.getText(), languageBox.getValue());
            }
        });

        // Add listeners (Dialog boxes) to the errorMessage property
        viewModel.errorMessageProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                Alert errorDialog = new Alert(Alert.AlertType.ERROR);
                errorDialog.setTitle("Error");
                errorDialog.setContentText(viewModel.getErrorMessage());
                errorDialog.showAndWait();
            }
        });

        // Add listeners (Toast Notification) to the successMessage property
        viewModel.successMessageProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                toastNotification.setText(viewModel.getSuccessMessage());
                toastNotification.showAndHide(2000);
            }
        });

    }


    public VBox getRoot() {
        return root;
    }

    public MonacoFX getEditorNode() {
        return editorNode;
    }

    public CreateCodeSnippetController getController() {
        return controller;
    }

    public void setController(CreateCodeSnippetController controller) {
        this.controller = controller;
    }
}
