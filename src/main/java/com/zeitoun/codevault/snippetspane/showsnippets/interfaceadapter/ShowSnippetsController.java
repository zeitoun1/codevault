package com.zeitoun.codevault.snippetspane.showsnippets.interfaceadapter;

import com.zeitoun.codevault.shared.AppContext;
import com.zeitoun.codevault.snippetspane.showsnippets.usecase.ShowSnippetsInteractor;

public class ShowSnippetsController {

    private final ShowSnippetsInteractor showSnippetsInteractor;
    private final AppContext appContext;

    public ShowSnippetsController(ShowSnippetsInteractor showSnippetsInteractor, AppContext appContext) {
        this.showSnippetsInteractor = showSnippetsInteractor;
        this.appContext = appContext;
    }

    public void showSnippets(String selectedFolder) {
        showSnippetsInteractor.getAppContext().setSelectedFolder(selectedFolder); // setting the selected folder for the CreateSnippet and GetSnippet use cases
        showSnippetsInteractor.execute(selectedFolder);
    }
}
