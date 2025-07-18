package com.zeitoun.codevault.codesnippet.view;

import com.zeitoun.codevault.ToastNotification;
import com.zeitoun.codevault.app.SceneManager;
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
    private final CreateCodeSnippetViewModel createCodeSnippetViewModel;
    private final GetSnippetViewModel getSnippetViewModel;
    private ToastNotification toastNotification;



    private final String name = "create snippet";
    private SceneManager sceneManager;

    public CreateCodeSnippetView(CreateCodeSnippetViewModel createCodeSnippetViewModel, GetSnippetViewModel getSnippetViewModel) {
        this.createCodeSnippetViewModel = createCodeSnippetViewModel;
        this.getSnippetViewModel = getSnippetViewModel;


        // initializing child nodes
        this.nameBox = new TextField();
        nameBox.setPrefWidth(1000);
        nameBox.setPromptText("Snippet name");
        nameBox.setFont(new Font(20));


        this.languageBox = new ComboBox<>(createCodeSnippetViewModel.getEditorLanguages());

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
        createCodeSnippetViewModel.errorMessageProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                Alert errorDialog = new Alert(Alert.AlertType.ERROR);
                errorDialog.setTitle("Error");
                errorDialog.setContentText(createCodeSnippetViewModel.getErrorMessage());
                errorDialog.showAndWait();
            }
        });

        // Add listeners (Toast Notification) to the successMessage property
        createCodeSnippetViewModel.successMessageProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                toastNotification.setText(createCodeSnippetViewModel.getSuccessMessage());
                toastNotification.showAndHide(2000);
            }
        });

        // Add listener to the code snippet name property and also update all other ui elements
        getSnippetViewModel.getNameProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                nameBox.setText(getSnippetViewModel.getName());
                editorNode.getEditor().getDocument().setText(getSnippetViewModel.getCode());
                descriptionBox.setText(getSnippetViewModel.getDescription());
                languageBox.setValue(getSnippetViewModel.getLanguage());
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

    public String getName() {
        return name;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
