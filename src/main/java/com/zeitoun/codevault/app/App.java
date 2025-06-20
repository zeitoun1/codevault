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

        primaryStage.setTitle("Code Vault");
        primaryStage.show();
    }

}

