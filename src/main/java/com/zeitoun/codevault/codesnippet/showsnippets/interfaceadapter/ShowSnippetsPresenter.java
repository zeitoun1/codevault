package com.zeitoun.codevault.codesnippet.showsnippets.interfaceadapter;

import com.zeitoun.codevault.codesnippet.showsnippets.usecase.ShowSnippetsOutputBoundary;
import com.zeitoun.codevault.codesnippet.view.SnippetsPaneViewModel;

import java.util.List;

public class ShowSnippetsPresenter implements ShowSnippetsOutputBoundary {

    private SnippetsPaneViewModel snippetsPaneViewModel;

    public ShowSnippetsPresenter(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;
    }

    @Override
    public void setSnippetsList(List<String> snippets) {
        snippetsPaneViewModel.getSnippets().setAll(snippets);
    }
}
