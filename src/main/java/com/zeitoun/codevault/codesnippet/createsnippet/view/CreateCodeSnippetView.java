package com.zeitoun.codevault.codesnippet.createsnippet.view;

import com.zeitoun.codevault.ToastNotification;
import com.zeitoun.codevault.app.SceneManager;
import com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter.CreateCodeSnippetController;
import com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter.GetSnippetController;
import com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter.ShowSnippetsController;
import eu.mihosoft.monacofx.MonacoFX;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class CodeSnippetView {
    private final HBox root;
    private final VBox snippetNode;
    private final AnchorPane topNode;
    private final TextField nameBox;
    private final ComboBox<String> languageBox;
    private final StackPane stackPane;
    
    private final MonacoFX editorNode;
    private ToastNotification toastNotification;
    private final HBox bottomNode;
    private final TextArea descriptionBox;
    private final Button saveButton;

    private final ListView<String> snippetsList;
    private final HBox header;
    private final Label label;
    private final Button addButton;
    private final VBox snippetsPane;

    private ShowSnippetsController showSnippetsController;

<<<<<<<< HEAD:src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CodeSnippetView.java
    private GetSnippetController getSnippetController;

    private  CreateCodeSnippetController createCodeSnippetController;
    private final CreateCodeSnippetViewModel createCodeSnippetViewModel;
    private final GetSnippetViewModel getSnippetViewModel;
========
    private  CreateCodeSnippetController controller;
    private final CreateCodeSnippetViewModel viewModel;
    private ToastNotification toastNotification;
>>>>>>>> parent of 337ea06 (fixed bug with chosen language in get snippet usecase.):src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CreateCodeSnippetView.java

    private final SnippetsPaneViewModel snippetsPaneViewModel;

    private SceneManager sceneManager;
<<<<<<<< HEAD:src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CodeSnippetView.java
    private final String name = "create snippet";
    public CodeSnippetView(CreateCodeSnippetViewModel createCodeSnippetViewModel, GetSnippetViewModel getSnippetViewModel, SnippetsPaneViewModel snippetsPaneViewModel) {
        this.createCodeSnippetViewModel = createCodeSnippetViewModel;
        this.getSnippetViewModel = getSnippetViewModel;
        this.snippetsPaneViewModel = snippetsPaneViewModel;
========

    public CreateCodeSnippetView(CreateCodeSnippetViewModel viewModel) {
        this.viewModel = viewModel;
>>>>>>>> parent of 337ea06 (fixed bug with chosen language in get snippet usecase.):src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CreateCodeSnippetView.java


        // initializing snippetNode (where snippet creations takes place and where the contents of snippets are viewed)

        // top node
        nameBox = new TextField();
        nameBox.setPrefWidth(1000);
        nameBox.setPromptText("Snippet name");
        nameBox.setFont(new Font(20));

        languageBox = new ComboBox<>(createCodeSnippetViewModel.getEditorLanguages());

<<<<<<<< HEAD:src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CodeSnippetView.java
        topNode = new AnchorPane(this.nameBox, this.languageBox);
        AnchorPane.setTopAnchor(languageBox, 2.0);
        AnchorPane.setRightAnchor(languageBox, 20.0);
========
        this.languageBox = new ComboBox<>(viewModel.getEditorLanguages());
>>>>>>>> parent of 337ea06 (fixed bug with chosen language in get snippet usecase.):src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CreateCodeSnippetView.java

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

        snippetNode = new VBox(topNode, stackPane, bottomNode);


        // snippets pane
        snippetsList = new ListView<>(snippetsPaneViewModel.getSnippets());
        addButton = new Button("add snippet");
        label = new Label("snippets");
        header = new HBox(label, addButton);
        snippetsPane = new VBox(header, snippetsList);
        root = new HBox(snippetsList, snippetNode);
        HBox.setHgrow(snippetNode, Priority.ALWAYS);


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
                createCodeSnippetController.execute(editorNode.getEditor().getDocument().getText(), nameBox.getText(), descriptionBox.getText(), languageBox.getValue());
            }
        });

<<<<<<<< HEAD:src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CodeSnippetView.java
        // showing error message when CreateCodeSnippet is unsuccessful
        createCodeSnippetViewModel.errorMessageProperty().addListener(new InvalidationListener() {
========
        // Add listeners (Dialog boxes) to the errorMessage property
        viewModel.errorMessageProperty().addListener(new InvalidationListener() {
>>>>>>>> parent of 337ea06 (fixed bug with chosen language in get snippet usecase.):src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CreateCodeSnippetView.java
            @Override
            public void invalidated(Observable observable) {
                Alert errorDialog = new Alert(Alert.AlertType.ERROR);
                errorDialog.setTitle("Error");
                errorDialog.setContentText(viewModel.getErrorMessage());
                errorDialog.showAndWait();
            }
        });

<<<<<<<< HEAD:src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CodeSnippetView.java
        // showing success message when CreateCodeSnippet use case is successful
        createCodeSnippetViewModel.successMessageProperty().addListener(new InvalidationListener() {
========
        // Add listeners (Toast Notification) to the successMessage property
        viewModel.successMessageProperty().addListener(new InvalidationListener() {
>>>>>>>> parent of 337ea06 (fixed bug with chosen language in get snippet usecase.):src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CreateCodeSnippetView.java
            @Override
            public void invalidated(Observable observable) {
                toastNotification.setText(viewModel.getSuccessMessage());
                toastNotification.showAndHide(2000);
            }
        });

<<<<<<<< HEAD:src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CodeSnippetView.java

        // adding listener to start the GetSnippet use case upon selection in the snippetsList
        snippetsList.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                getSnippetController.getSnippet(snippetsList.getSelectionModel().getSelectedItem());
            }
        });

        // Updating the UI elements in the snippetNode to get the selected snippet
        getSnippetViewModel.getNameProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                nameBox.setText(getSnippetViewModel.getName());
                editorNode.getEditor().getDocument().setText(getSnippetViewModel.getCode());
                descriptionBox.setText(getSnippetViewModel.getDescription());
                languageBox.setValue(getSnippetViewModel.getLanguage());
            }
        });

========
>>>>>>>> parent of 337ea06 (fixed bug with chosen language in get snippet usecase.):src/main/java/com/zeitoun/codevault/codesnippet/createsnippet/view/CreateCodeSnippetView.java
    }

    public HBox getRoot() {
        return root;
    }

    public MonacoFX getEditorNode() {
        return editorNode;
    }

    public CreateCodeSnippetController getCreateCodeSnippetController() {
        return createCodeSnippetController;
    }

    public void setCreateCodeSnippetController(CreateCodeSnippetController createCodeSnippetController) {
        this.createCodeSnippetController = createCodeSnippetController;
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
