package com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter;

import com.zeitoun.codevault.codesnippet.getsnippet.usecase.GetSnippetInteractor;
import com.zeitoun.codevault.shared.AppContext;

public class GetSnippetController {

    private final GetSnippetInteractor getSnippetInteractor;
    private final AppContext appContext;

    public GetSnippetController(GetSnippetInteractor getSnippetInteractor, AppContext appContext) {
        this.getSnippetInteractor = getSnippetInteractor;
        this.appContext = appContext;
    }

    public void getSnippet(String snippetName) {
        appContext.setSelectedSnippet(snippetName);
        getSnippetInteractor.execute(snippetName, appContext.getSelectedFolder());
    }


}
