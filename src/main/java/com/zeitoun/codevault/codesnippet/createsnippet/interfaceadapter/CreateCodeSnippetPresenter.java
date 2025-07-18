package com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter;

import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetOutputBoundary;
import com.zeitoun.codevault.codesnippet.view.CreateCodeSnippetViewModel;

/**
 * A Presenter for creating code snippets
 * Shows success and error messages upon creation
 */

public class CreateCodeSnippetPresenter implements CreateCodeSnippetOutputBoundary {
    private CreateCodeSnippetViewModel createCodeSnippetViewModel;

    public CreateCodeSnippetPresenter(CreateCodeSnippetViewModel createCodeSnippetViewModel) {
        this.createCodeSnippetViewModel = createCodeSnippetViewModel;
    }

    @Override
    public void showSuccessMessage(String success) {
        createCodeSnippetViewModel.setSuccessMessage(success);
    }

    @Override
    public void showErrorMessage(String error) {
        createCodeSnippetViewModel.setErrorMessage(error);
    }

    public CreateCodeSnippetViewModel getCreateCodeSnippetViewModel() {
        return createCodeSnippetViewModel;
    }

    public void setCreateCodeSnippetViewModel(CreateCodeSnippetViewModel createCodeSnippetViewModel) {
        this.createCodeSnippetViewModel = createCodeSnippetViewModel;
    }
}
