package com.zeitoun.codevault.codesnippet.view;

import com.zeitoun.codevault.ToastNotification;
import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.updatesnippet.interfaceadapter.UpdateCodeSnippetController;
import com.zeitoun.codevault.codesnippet.getsnippet.GetSnippetViewModel;
import com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter.GetSnippetController;
import eu.mihosoft.monacofx.MonacoFX;
import com.zeitoun.codevault.snippetspane.showsnippets.interfaceadapter.ShowSnippetsController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;


public class UpdateCodeSnippetView {
    private final VBox root;
    private final ComboBox<String> languageBox;
    private final Label languageLabel;
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

        toastNotification = new ToastNotification(stackPane);
        toastNotification.setVisible(false);


        // bottom node
        languageBox = new ComboBox<>(updateCodeSnippetViewModel.getEditorLanguages());
        languageLabel = new Label("Language:");
        descriptionBox = new TextArea();
        descriptionBox.setPromptText("Description");
        descriptionBox.setFont(new Font(18));

        saveButton = new Button("Save");
        saveButton.setFont(new Font(18.0));
        bottomNode = new HBox(descriptionBox, languageLabel, this.languageBox, saveButton);

        root = new VBox(stackPane, bottomNode);


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

        // showing error message when CreateCodeSnippet is unsuccessful
        updateCodeSnippetViewModel.errorMessageProperty().addListener(new ChangeListener<String>() {
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
        updateCodeSnippetViewModel.successMessageProperty().addListener((observableValue, oldVal, newVal) -> {
            if(!newVal.isEmpty()){
                ToastNotification notification = new ToastNotification(stackPane);
                notification.setText(updateCodeSnippetViewModel.successMessageProperty().getValue());
                updateCodeSnippetViewModel.successMessageProperty().setValue("");
                notification.showAndHide(1000);
            }

        });

        // Updating the UI elements in the snippetNode to get the selected snippet
        getSnippetViewModel.getNameProperty().addListener(new ChangeListener<String>() {
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
