package com.zeitoun.codevault.app;

import com.zeitoun.codevault.codesnippet.createsnippet.CreateCodeSnippetController;
import com.zeitoun.codevault.codesnippet.createsnippet.CreateCodeSnippetInteractor;
import com.zeitoun.codevault.codesnippet.createsnippet.CreateCodeSnippetOutputBoundary;
import com.zeitoun.codevault.codesnippet.createsnippet.CreateCodeSnippetView;
import com.zeitoun.codevault.codesnippet.dataaccess.SnippetRepository;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SnippetRepository snippetRepository = new SnippetRepository() {
            @Override
            public String saveSnippet(String code, String name, String description, String language) {
                return "";
            }
        };
        CreateCodeSnippetOutputBoundary outputBoundary = new CreateCodeSnippetOutputBoundary() {
            @Override
            public void SwitchToHomeView() {

            }

            @Override
            public void showErrorMessage(String error) {

            }
        };
        CreateCodeSnippetInteractor createCodeSnippetInteractor = new CreateCodeSnippetInteractor(snippetRepository, outputBoundary);
        CreateCodeSnippetController controller = new CreateCodeSnippetController(createCodeSnippetInteractor);
        CreateCodeSnippetView view = new CreateCodeSnippetView(controller);
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Code Vault");
        primaryStage.show();
    }

}

