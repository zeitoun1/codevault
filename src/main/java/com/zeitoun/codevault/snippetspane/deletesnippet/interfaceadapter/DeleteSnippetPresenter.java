package com.zeitoun.codevault.snippetspane.deletesnippet.interfaceadapter;

import com.zeitoun.codevault.snippetspane.deletesnippet.usecase.DeleteSnippetOutputBoundary;
import com.zeitoun.codevault.snippetspane.view.SnippetsPaneViewModel;

public class DeleteSnippetPresenter implements DeleteSnippetOutputBoundary {
    private final SnippetsPaneViewModel snippetsPaneViewModel;

    public DeleteSnippetPresenter(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;
    }

    @Override
    public void removeSnippetFromList(int selectedIndex) {
        snippetsPaneViewModel.getSnippetsList().remove(selectedIndex);
    }
}