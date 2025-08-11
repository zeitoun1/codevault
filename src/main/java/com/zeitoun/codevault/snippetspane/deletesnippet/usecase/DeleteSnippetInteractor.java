package com.zeitoun.codevault.snippetspane.deletesnippet.usecase;

import com.zeitoun.codevault.database.repostiories.SnippetRepository;

public class DeleteSnippetInteractor {
    private final DeleteSnippetOutputBoundary deleteSnippetOutputBoundary;
    private final SnippetRepository snippetRepository;

    public DeleteSnippetInteractor(DeleteSnippetOutputBoundary deleteSnippetOutputBoundary, SnippetRepository snippetRepository) {
        this.deleteSnippetOutputBoundary = deleteSnippetOutputBoundary;
        this.snippetRepository = snippetRepository;
    }

    public void execute(String selectedSnippet, String selectedFolder, int selectedindex) {
        snippetRepository.deleteSnippet(selectedSnippet, selectedFolder);
        deleteSnippetOutputBoundary.removeSnippetFromList(selectedindex);
    }
}
