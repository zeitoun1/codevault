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

    public void execute(CreateCodeSnippetInputData newSnippet) {
        if(newSnippet.getName().isEmpty()) {
          this.createCodeSnippetOutputBoundary.showErrorMessage("Empty code snippet name is not allowed.");
        } else if (newSnippet.getLanguage().isEmpty()) {
            this.createCodeSnippetOutputBoundary.showErrorMessage("Language is missing.");
        } else if (repository.isMember(newSnippet.getName(), newSnippet.getLanguage())) {
            this.createCodeSnippetOutputBoundary.showErrorMessage("A code snippet with that name and language already exists.");
        } else {
            this.repository.saveSnippet(newSnippet.getCode(), newSnippet.getName(), newSnippet.getDescription(), newSnippet.getLanguage());
        }
    }
}
