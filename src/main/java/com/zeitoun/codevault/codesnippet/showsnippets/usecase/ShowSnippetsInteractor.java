package com.zeitoun.codevault.codesnippet.showsnippets.usecase;

import com.zeitoun.codevault.database.SnippetRepository;

public class ShowSnippetsInteractor {

    private final ShowSnippetsOutputBoundary showSnippetsOutputBoundary;
    private final SnippetRepository snippetRepository;

    public ShowSnippetsInteractor(SnippetRepository snippetRepository, ShowSnippetsOutputBoundary showSnippetsOutputBoundary) {
        this.snippetRepository = snippetRepository;
        this.showSnippetsOutputBoundary = showSnippetsOutputBoundary;
    }

    public void showSnippets(String selectedFolder) {
        showSnippetsOutputBoundary.setSnippetsList(snippetRepository.getSnippets(selectedFolder));
    }

}
