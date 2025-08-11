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
        // the view only listens to the name property as that is unique for every folder and it changes on selection
        getSnippetViewModel.setName(getSnippetOutputData.getName());
    }
}
