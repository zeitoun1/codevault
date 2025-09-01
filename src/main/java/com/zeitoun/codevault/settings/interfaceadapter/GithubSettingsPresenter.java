package com.zeitoun.codevault.settings.interfaceadapter;

import com.zeitoun.codevault.codesnippet.view.UpdateCodeSnippetViewModel;
import com.zeitoun.codevault.settings.usecase.GithubSettingsOutputBoundary;

public class GithubSettingsPresenter implements GithubSettingsOutputBoundary {
    private final UpdateCodeSnippetViewModel updateCodeSnippetViewModel;

    public GithubSettingsPresenter(UpdateCodeSnippetViewModel updateCodeSnippetViewModel) {
        this.updateCodeSnippetViewModel = updateCodeSnippetViewModel;
    }

    @Override
    public void showErrorMessage(String error) {
        updateCodeSnippetViewModel.errorMessageProperty().setValue(error);
    }
}
