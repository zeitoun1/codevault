package com.zeitoun.codevault.snippetspane.renamesnippet.interfaceadapter;

import com.zeitoun.codevault.snippetspane.renamesnippet.usecase.RenameSnippetOutputBoundary;
import com.zeitoun.codevault.snippetspane.view.SnippetsPaneViewModel;

public class RenameSnippetPresenter implements RenameSnippetOutputBoundary {
    private final SnippetsPaneViewModel snippetsPaneViewModel;

    public RenameSnippetPresenter(SnippetsPaneViewModel snippetsPaneViewModel) {
        this.snippetsPaneViewModel = snippetsPaneViewModel;
    }


    @Override
    public void setSnippet(int index, String newFolderName) {
        snippetsPaneViewModel.getSnippetsList().set(index, newFolderName);
    }

    @Override
    public void showErrorMessage(String error) {
        snippetsPaneViewModel.setError(error);
    }
}
