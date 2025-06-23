package com.zeitoun.codevault.codesnippet.createsnippet.view;

import com.zeitoun.codevault.ToastNotification;
import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetController;
import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetViewModel;
import eu.mihosoft.monacofx.MonacoFX;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class CreateCodeSnippetView {
    private final Scene scene;
    private VBox root;
    private AnchorPane topNode;
    private TextField nameBox;
    private ComboBox<String> languageBox;
    private StackPane stackPane;
    private MonacoFX editorNode;
    private HBox bottomNode;
    private TextArea descriptionBox;
    private Button saveButton;
    private CreateCodeSnippetController controller;
    private CreateCodeSnippetViewModel viewModel;
    private ToastNotification toastNotification;


    public CreateCodeSnippetView(CreateCodeSnippetController controller, CreateCodeSnippetViewModel viewModel) {

        // Creating a Background
        Color darkMode = Color.rgb(20, 20, 20);
        BackgroundFill darkBackgroundFill = new BackgroundFill(darkMode, CornerRadii.EMPTY, Insets.EMPTY);
        Background darkBackground = new Background(darkBackgroundFill);

        this.controller = controller;
        this.viewModel = viewModel;


        // initializing child nodes
        this.nameBox = new TextField();
        nameBox.setPrefWidth(1000);
        nameBox.setPromptText("Snippet name");
        nameBox.setFont(new Font(20));
        nameBox.setBackground(darkBackground);

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
        descriptionBox.setBackground(darkBackground);

        this.saveButton = new Button("Save");
        saveButton.setFont(new Font(18.0));


        // initializing parent nodes and scene
        AnchorPane.setRightAnchor(languageBox, 20.0);
        AnchorPane.setTopAnchor(languageBox, 2.0);
        this.topNode = new AnchorPane(this.nameBox, this.languageBox);
        topNode.setBackground(darkBackground);


        this.stackPane = new StackPane(this.editorNode);
        VBox.setVgrow(stackPane, Priority.ALWAYS);

        this.toastNotification = new ToastNotification(stackPane);
        toastNotification.setVisible(false);

        this.bottomNode = new HBox(this.descriptionBox, this.saveButton);
        bottomNode.setBackground(darkBackground);

        this.root = new VBox(this.topNode, this.stackPane, this.bottomNode);
        this.scene = new Scene(root);
        editorNode.requestFocus();



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

    public Scene getScene() {
        return scene;
    }


}
