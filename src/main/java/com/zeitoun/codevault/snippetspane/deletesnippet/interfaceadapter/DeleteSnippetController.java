package com.zeitoun.codevault.snippetspane.deletesnippet.interfaceadapter;

import com.zeitoun.codevault.shared.AppContext;
import com.zeitoun.codevault.snippetspane.deletesnippet.usecase.DeleteSnippetInteractor;

public class DeleteSnippetController {
    private final DeleteSnippetInteractor deleteSnippetInteractor;
    private final AppContext appContext;

    public DeleteSnippetController(DeleteSnippetInteractor deleteSnippetInteractor, AppContext appContext) {
        this.deleteSnippetInteractor = deleteSnippetInteractor;
        this.appContext = appContext;
    }

    public void deleteSnippet(int selectedIndex) {
        deleteSnippetInteractor.execute(appContext.getSelectedSnippet(), appContext.getSelectedFolder(), selectedIndex);
    }
}