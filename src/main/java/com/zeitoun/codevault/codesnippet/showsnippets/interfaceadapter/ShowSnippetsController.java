package com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter;

import com.zeitoun.codevault.codesnippet.showsnippets.usecase.ShowSnippetsInteractor;

public class ShowSnippetsController {

    private ShowSnippetsInteractor showSnippetsInteractor;

    public ShowSnippetsController(ShowSnippetsInteractor showSnippetsInteractor) {
        this.showSnippetsInteractor = showSnippetsInteractor;
    }

    public void execute(String selectedFolder) {
        showSnippetsInteractor.showSnippets(selectedFolder);
    }
}
