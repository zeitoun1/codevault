package com.zeitoun.codevault.snippetspane.showsnippets.usecase;

import com.zeitoun.codevault.database.SnippetRepository;
import com.zeitoun.codevault.shared.AppContext;

public class ShowSnippetsInteractor {

    private final ShowSnippetsOutputBoundary showSnippetsOutputBoundary;
    private final SnippetRepository snippetRepository;
    private final AppContext appContext;

    public ShowSnippetsInteractor(SnippetRepository snippetRepository, ShowSnippetsOutputBoundary showSnippetsOutputBoundary, AppContext appContext) {
        this.snippetRepository = snippetRepository;
        this.showSnippetsOutputBoundary = showSnippetsOutputBoundary;
        this.appContext = appContext;
    }

    public void execute(String selectedFolder) {
        showSnippetsOutputBoundary.setSnippetsList(snippetRepository.getSnippets(selectedFolder));
    }

    public AppContext getAppContext() {
        return appContext;
    }
}
