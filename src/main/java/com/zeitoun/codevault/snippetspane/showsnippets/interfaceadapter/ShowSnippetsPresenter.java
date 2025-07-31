package com.zeitoun.codevault.snippetspane.showsnippets.interfaceadapter;

import com.zeitoun.codevault.snippetspane.showsnippets.usecase.ShowSnippetsOutputBoundary;
import com.zeitoun.codevault.snippetspane.view.SnippetsPaneViewModel;

import java.util.List;

public class ShowSnippetsPresenter implements ShowSnippetsOutputBoundary {

    private final SnippetsPaneViewModel snippetsPaneViewModel;

    public ShowSnippetsPresenter(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;
    }

    @Override
    public void setSnippetsList(List<String> snippets) {
        snippetsPaneViewModel.setSnippetsList(snippets);
    }
}
