package com.zeitoun.codevault.codesnippet.getsnippet.interfaceadapter;

import com.zeitoun.codevault.codesnippet.getsnippet.usecase.GetSnippetOutputBoundary;
import com.zeitoun.codevault.codesnippet.getsnippet.usecase.GetSnippetOutputData;
import com.zeitoun.codevault.codesnippet.view.GetSnippetViewModel;

public class GetSnippetPresenter implements GetSnippetOutputBoundary {
    private final GetSnippetViewModel getSnippetViewModel;

    public GetSnippetPresenter(GetSnippetViewModel getSnippetViewModel) {
        this.getSnippetViewModel = getSnippetViewModel;
    }

    @Override
    public void loadSnippet(GetSnippetOutputData getSnippetOutputData) {
        // we only invalidate the name property, as we will update all other view elements at once
        getSnippetViewModel.setName(getSnippetOutputData.getName());
        getSnippetViewModel.setCode(getSnippetOutputData.getCode());
        getSnippetViewModel.setDescription(getSnippetOutputData.getDescription());
        getSnippetViewModel.setLanguage(getSnippetOutputData.getLanguage());
    }
}
