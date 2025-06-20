package com.zeitoun.codevault.codesnippet.createsnippet;

public class CreateCodeSnippetPresenter implements CreateCodeSnippetOutputBoundary{
    private CreateCodeSnippetViewModel createCodeSnippetViewModel;

    public CreateCodeSnippetPresenter(CreateCodeSnippetViewModel createCodeSnippetViewModel) {
        this.createCodeSnippetViewModel = createCodeSnippetViewModel;
    }

    @Override
    public void SwitchToHomeView() {

    }

    @Override
    public void showErrorMessage(String error) {
        createCodeSnippetViewModel.errorMessage.setValue(error);
    }

    @Override
    public void showReplaceConfirmation(String message) {
        createCodeSnippetViewModel.ConfirmationMessage.setValue(message);
    }

    public CreateCodeSnippetViewModel getCreateCodeSnippetViewModel() {
        return createCodeSnippetViewModel;
    }

    public void setCreateCodeSnippetViewModel(CreateCodeSnippetViewModel createCodeSnippetViewModel) {
        this.createCodeSnippetViewModel = createCodeSnippetViewModel;
    }
}
