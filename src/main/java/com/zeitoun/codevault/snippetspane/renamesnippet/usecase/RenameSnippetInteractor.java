package com.zeitoun.codevault.snippetspane.renamesnippet.usecase;

import com.zeitoun.codevault.database.repostiories.SnippetRepository;

public class RenameSnippetInteractor {
    private final RenameSnippetOutputBoundary renameSnippetOutputBoundary;
    private final SnippetRepository snippetRepository;

    public RenameSnippetInteractor(RenameSnippetOutputBoundary renameSnippetOutputBoundary, SnippetRepository snippetRepository) {
        this.renameSnippetOutputBoundary = renameSnippetOutputBoundary;
        this.snippetRepository = snippetRepository;
    }

    public void execute(int index, String oldSnippetName, String newSnippetName, String selectedFolder) {
        if(snippetRepository.isMember(newSnippetName, selectedFolder)) {
            renameSnippetOutputBoundary.showErrorMessage("A snippet with that name already exists");
        } else {
            snippetRepository.renameSnippet(oldSnippetName, newSnippetName, selectedFolder);
            renameSnippetOutputBoundary.setSnippet(index, newSnippetName);
        }
    }
}
