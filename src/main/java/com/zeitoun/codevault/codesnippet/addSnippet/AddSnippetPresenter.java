package com.zeitoun.codevault.codesnippet.addSnippet;

import com.zeitoun.codevault.codesnippet.showsnippets.view.SnippetsPaneViewModel;

public class AddSnippetPresenter implements AddSnippetOutputBoundary{
    private final SnippetsPaneViewModel snippetsPaneViewModel;

    public AddSnippetPresenter(SnippetsPaneViewModel snippetsPaneViewModel){
        this.snippetsPaneViewModel = snippetsPaneViewModel;
    }

    @Override
    public void addToSnippetList(String snippetName) {
        snippetsPaneViewModel.getSnippets().add(0, snippetName);
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }
}
