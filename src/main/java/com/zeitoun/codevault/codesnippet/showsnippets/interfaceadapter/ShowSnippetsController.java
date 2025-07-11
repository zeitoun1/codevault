package com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter;

import com.zeitoun.codevault.codesnippet.showsnippets.usecase.ShowSnippetsInteractor;

public class ShowSnippetsController {

    private final ShowSnippetsInteractor showSnippetsInteractor;

    public ShowSnippetsController(ShowSnippetsInteractor showSnippetsInteractor) {
        this.showSnippetsInteractor = showSnippetsInteractor;
    }

    public void showSnippets(String selectedFolder) {
        showSnippetsInteractor.getAppContext().setSelectedFolder(selectedFolder); // setting the selected folder for the CreateSnippet and GetSnippet use cases
        showSnippetsInteractor.execute(selectedFolder);
    }
}
