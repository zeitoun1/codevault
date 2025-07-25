package com.zeitoun.codevault.codesnippet.updatesnippet.interfaceadapter;

import com.zeitoun.codevault.codesnippet.updatesnippet.usecase.UpdateCodeSnippetOutputBoundary;
import com.zeitoun.codevault.codesnippet.updatesnippet.view.UpdateCodeSnippetViewModel;

/**
 * A Presenter for creating code snippets
 * Shows success and error messages upon creation
 */

public class UpdateCodeSnippetPresenter implements UpdateCodeSnippetOutputBoundary {
    private UpdateCodeSnippetViewModel updateCodeSnippetViewModel;

    public UpdateCodeSnippetPresenter(UpdateCodeSnippetViewModel updateCodeSnippetViewModel) {
        this.updateCodeSnippetViewModel = updateCodeSnippetViewModel;
    }

    @Override
    public void showSuccessMessage(String success) {
        updateCodeSnippetViewModel.setSuccessMessage(success);
    }

    @Override
    public void showErrorMessage(String error) {
        updateCodeSnippetViewModel.setErrorMessage(error);
    }

    public UpdateCodeSnippetViewModel getCreateCodeSnippetViewModel() {
        return updateCodeSnippetViewModel;
    }

    public void setCreateCodeSnippetViewModel(UpdateCodeSnippetViewModel updateCodeSnippetViewModel) {
        this.updateCodeSnippetViewModel = updateCodeSnippetViewModel;
    }
}
