package com.zeitoun.codevault.codesnippet.addSnippet;

import com.zeitoun.codevault.database.SnippetRepository;

/**
 * Interactor responsible for adding a snippet with just a name.
 */

public class AddSnippetInteractor {
    private final AddSnippetOutputBoundary addSnippetOutputBoundary;
    private final SnippetRepository snippetRepository;
    public AddSnippetInteractor(AddSnippetOutputBoundary addSnippetOutputBoundary, SnippetRepository snippetRepository){
        this.addSnippetOutputBoundary = addSnippetOutputBoundary;
        this.snippetRepository = snippetRepository;
    }

    public void execute(String snippetName, String selectedFolder) {
        if(snippetRepository.isMember(snippetName, selectedFolder)){
            addSnippetOutputBoundary.showErrorMessage("A snippet with that name already exists.");
        } else {
            snippetRepository.saveSnippet(null, snippetName, null, null, selectedFolder);
            addSnippetOutputBoundary.addToSnippetList(snippetName);
        }
    }
}
