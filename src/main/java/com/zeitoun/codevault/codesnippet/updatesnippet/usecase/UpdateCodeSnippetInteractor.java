package com.zeitoun.codevault.codesnippet.updatesnippet.usecase;

import com.zeitoun.codevault.database.repostiories.SnippetRepository;

/**
 * Interactor class responsible for creating code snippets.
 */
public class UpdateCodeSnippetInteractor {
    private final SnippetRepository repository;
    private final UpdateCodeSnippetOutputBoundary updateCodeSnippetOutputBoundary;


    public UpdateCodeSnippetInteractor(SnippetRepository repository, UpdateCodeSnippetOutputBoundary updateCodeSnippetOutputBoundary) {
        this.repository = repository;
        this.updateCodeSnippetOutputBoundary = updateCodeSnippetOutputBoundary;
    }

    public SnippetRepository getRepository() {
        return repository;
    }


    public UpdateCodeSnippetOutputBoundary getCreateCodeSnippetOutputBoundary() {
        return updateCodeSnippetOutputBoundary;
    }

    public void execute(UpdateCodeSnippetInputData newSnippet) {
        if (newSnippet.getLanguage() == null) {
            this.updateCodeSnippetOutputBoundary.showErrorMessage("Language is missing.");
        } else if (newSnippet.getFolder() == null) {
            this.updateCodeSnippetOutputBoundary.showErrorMessage("folder is missing");
        } else {
            this.repository.updateSnippet(newSnippet.getSelectedSnippet(), newSnippet.getSelectedFolder(), newSnippet.getCode(), newSnippet.getDescription(), newSnippet.getLanguage());
            this.updateCodeSnippetOutputBoundary.showSuccessMessage("Snippet saved successfully.");
        }
    }
}
