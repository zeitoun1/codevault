package com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter;

import com.zeitoun.codevault.codesnippet.view.GetSnippetViewModel;
import com.zeitoun.codevault.codesnippet.getsnippet.usecase.GetSnippetOutputBoundary;
import com.zeitoun.codevault.codesnippet.getsnippet.usecase.GetSnippetOutputData;


public class GetSnippetPresenter implements GetSnippetOutputBoundary {
    private final GetSnippetViewModel getSnippetViewModel;

    public GetSnippetPresenter(GetSnippetViewModel getSnippetViewModel) {
        this.getSnippetViewModel = getSnippetViewModel;
    }

    @Override
    public void loadSnippet(GetSnippetOutputData getSnippetOutputData) {
        getSnippetViewModel.setCode(getSnippetOutputData.getCode());
        getSnippetViewModel.setDescription(getSnippetOutputData.getDescription());
        getSnippetViewModel.setLanguage(getSnippetOutputData.getLanguage());
        getSnippetViewModel.nameProperty().setValue(getSnippetOutputData.getName());
    }
}
