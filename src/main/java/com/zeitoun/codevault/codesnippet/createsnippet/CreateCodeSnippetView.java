package com.zeitoun.codevault.codesnippet.createsnippet;

import eu.mihosoft.monacofx.MonacoFX;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
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


    public CreateCodeSnippetView(CreateCodeSnippetController controller) {

        this.controller = controller;

        // initializing child nodes
        this.nameBox = new TextField();
        nameBox.setPrefWidth(1000);
        nameBox.setPromptText("Snippet name");
        nameBox.setFont(new Font(20));

        this.languageBox = new ComboBox<>();

        this.editorNode = new MonacoFX();
        editorNode.getEditor().setCurrentTheme("vs-dark");

        this.descriptionBox = new TextArea();
        descriptionBox.setPromptText("Description");
        descriptionBox.setFont(new Font(18));
        HBox.setHgrow(descriptionBox, Priority.ALWAYS);

        this.saveButton = new Button("Save");
        saveButton.setFont(new Font(18.0));

        // initializing parent nodes and scene
        AnchorPane.setRightAnchor(languageBox, 20.0);
        AnchorPane.setTopAnchor(languageBox, 2.0);
        this.topNode = new AnchorPane(this.nameBox, this.languageBox);

        this.bottomNode = new HBox(this.descriptionBox, this.saveButton);

        this.stackPane = new StackPane(this.editorNode);
        VBox.setVgrow(stackPane, Priority.ALWAYS);

        this.root = new VBox(this.topNode, this.stackPane, this.bottomNode);
        this.scene = new Scene(root);
        editorNode.requestFocus();

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.execute(editorNode.getEditor().getDocument().getText(), nameBox.getText(), descriptionBox.getText(), editorNode.getEditor().getCurrentLanguage());
            }
        });

    }

    public Scene getScene() {
        return scene;
    }

    public MonacoFX getEditorNode() {
        return editorNode;
    }

}
