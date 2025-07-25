package com.zeitoun.codevault.codesnippet.updatesnippet.view;

import com.zeitoun.codevault.ToastNotification;
import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.updatesnippet.interfaceadapter.UpdateCodeSnippetController;
import com.zeitoun.codevault.codesnippet.getsnippet.GetSnippetViewModel;
import com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter.GetSnippetController;
import com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter.ShowSnippetsController;
import com.zeitoun.codevault.codesnippet.showsnippets.view.SnippetsPaneViewModel;
import eu.mihosoft.monacofx.MonacoFX;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class UpdateCodeSnippetView {
    private final VBox root;
    private final AnchorPane topNode;
    private final TextField nameBox;
    private final ComboBox<String> languageBox;
    private final StackPane stackPane;
    
    private final MonacoFX editorNode;
    private final ToastNotification toastNotification;
    private final HBox bottomNode;
    private final TextArea descriptionBox;
    private final Button saveButton;

    private ShowSnippetsController showSnippetsController;
    private GetSnippetController getSnippetController;
    private UpdateCodeSnippetController updateCodeSnippetController;

    private final UpdateCodeSnippetViewModel updateCodeSnippetViewModel;
    private final GetSnippetViewModel getSnippetViewModel;
    private final SnippetsPaneViewModel snippetsPaneViewModel;


    private SceneManager sceneManager;
    private final String name = "create snippet";

    public UpdateCodeSnippetView(UpdateCodeSnippetViewModel updateCodeSnippetViewModel, GetSnippetViewModel getSnippetViewModel, SnippetsPaneViewModel snippetsPaneViewModel) {
        this.updateCodeSnippetViewModel = updateCodeSnippetViewModel;
        this.getSnippetViewModel = getSnippetViewModel;
        this.snippetsPaneViewModel = snippetsPaneViewModel;
        // initializing snippetNode (where snippet creations takes place and where the contents of snippets are viewed)

        // top node
        nameBox = new TextField();
        nameBox.setPrefWidth(1000);
        nameBox.setPromptText("Snippet name");
        nameBox.setFont(new Font(20));

        languageBox = new ComboBox<>(updateCodeSnippetViewModel.getEditorLanguages());

        topNode = new AnchorPane(this.nameBox, this.languageBox);
        AnchorPane.setTopAnchor(languageBox, 2.0);
        AnchorPane.setRightAnchor(languageBox, 20.0);

        // stackPane
        editorNode = new MonacoFX();
        editorNode.getEditor().setCurrentTheme("vs-dark");


        stackPane = new StackPane(editorNode);
        VBox.setVgrow(stackPane, Priority.ALWAYS);

        toastNotification = new ToastNotification(stackPane);
        toastNotification.setVisible(false);


        // bottom node
        descriptionBox = new TextArea();
        descriptionBox.setPromptText("Description");
        descriptionBox.setFont(new Font(18));

        saveButton = new Button("Save");
        saveButton.setFont(new Font(18.0));
        bottomNode = new HBox(descriptionBox, saveButton);

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
                updateCodeSnippetController.execute(editorNode.getEditor().getDocument().getText(), nameBox.getText(), descriptionBox.getText(), languageBox.getValue());
            }
        });

        // showing error message when CreateCodeSnippet is unsuccessful
        updateCodeSnippetViewModel.errorMessageProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                Alert errorDialog = new Alert(Alert.AlertType.ERROR);
                errorDialog.setTitle("Error");
                errorDialog.setContentText(updateCodeSnippetViewModel.getErrorMessage());
                errorDialog.showAndWait();
            }
        });

        // showing success message when CreateCodeSnippet use case is successful
        updateCodeSnippetViewModel.successMessageProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                toastNotification.setText(updateCodeSnippetViewModel.getSuccessMessage());
                toastNotification.showAndHide(2000);
            }
        });

        // Updating the UI elements in the snippetNode to get the selected snippet
        getSnippetViewModel.getNameProperty().
                addListener(new InvalidationListener() {
                    @Override
                    public void invalidated (Observable observable) {
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

    public UpdateCodeSnippetController getCreateCodeSnippetController() {
        return updateCodeSnippetController;
    }

    public void setCreateCodeSnippetController(UpdateCodeSnippetController updateCodeSnippetController) {
        this.updateCodeSnippetController = updateCodeSnippetController;
    }

    public String getName() {
        return name;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public ShowSnippetsController getShowSnippetsController() {
        return showSnippetsController;
    }

    public void setShowSnippetsController(ShowSnippetsController showSnippetsController) {
        this.showSnippetsController = showSnippetsController;
    }

    public void setGetSnippetController(GetSnippetController getSnippetController) {
        this.getSnippetController = getSnippetController;
    }
}
