package com.zeitoun.codevault.snippetspane.addSnippet;

import com.zeitoun.codevault.shared.AppContext;

public class AddSnippetController {
    private AddSnippetInteractor addSnippetInteractor;
    private AppContext appContext;

    public AddSnippetController(AddSnippetInteractor addSnippetInteractor, AppContext appContext){
        this.addSnippetInteractor = addSnippetInteractor;
        this.appContext = appContext;
    }

    public void addSnippet(String snippetName){
        addSnippetInteractor.execute(snippetName, appContext.getSelectedFolder());
    }
}
