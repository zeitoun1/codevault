package com.zeitoun.codevault.codesnippet.createsnippet;

import com.zeitoun.codevault.codesnippet.dataaccess.SnippetRepository;

/**
 * Interactor class responsible for creating code snippets.
 */
public class CreateCodeSnippetInteractor {
    private final SnippetRepository repository;
    private final CreateCodeSnippetOutputBoundary createCodeSnippetOutputBoundary;


    public CreateCodeSnippetInteractor(SnippetRepository repository, CreateCodeSnippetOutputBoundary createCodeSnippetOutputBoundary) {
        this.repository = repository;
        this.createCodeSnippetOutputBoundary = createCodeSnippetOutputBoundary;
    }

    public SnippetRepository getRepository() {
        return repository;
    }


    public CreateCodeSnippetOutputBoundary getCreateCodeSnippetOutputBoundary() {
        return createCodeSnippetOutputBoundary;
    }

    public void execute(CreateCodeSnippetInputData  newSnippet) {
        String result = this.repository.saveSnippet(newSnippet.getCode(), newSnippet.getName(), newSnippet.getDescription(), newSnippet.getLanguage());
        if(result.equals("success")) {
            this.createCodeSnippetOutputBoundary.SwitchToHomeView();
        } else {
            this.createCodeSnippetOutputBoundary.showErrorMessage(result);
        }

    }
}
