package com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter;

import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetOutputBoundary;

public class CreateCodeSnippetPresenter implements CreateCodeSnippetOutputBoundary {
    private CreateCodeSnippetViewModel createCodeSnippetViewModel;

    public CreateCodeSnippetPresenter(CreateCodeSnippetViewModel createCodeSnippetViewModel) {
        this.createCodeSnippetViewModel = createCodeSnippetViewModel;
    }

    @Override
    public void showSuccessMessage(String success) {
        createCodeSnippetViewModel.successMessage.setValue(success);
    }

    @Override
    public void showErrorMessage(String error) {
        createCodeSnippetViewModel.errorMessage.setValue(error);
    }

    public CreateCodeSnippetViewModel getCreateCodeSnippetViewModel() {
        return createCodeSnippetViewModel;
    }

    public void setCreateCodeSnippetViewModel(CreateCodeSnippetViewModel createCodeSnippetViewModel) {
        this.createCodeSnippetViewModel = createCodeSnippetViewModel;
    }
}
