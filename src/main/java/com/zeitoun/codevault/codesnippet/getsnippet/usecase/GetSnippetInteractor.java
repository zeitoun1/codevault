package com.zeitoun.codevault.codesnippet.getsnippet.usecase;

import com.zeitoun.codevault.codesnippet.entity.CodeSnippet;
import com.zeitoun.codevault.database.repostiories.SnippetRepository;

public class GetSnippetInteractor {
    private final SnippetRepository snippetRepository;
    private final GetSnippetOutputBoundary getSnippetOutputBoundary;


    public GetSnippetInteractor(SnippetRepository snippetRepository, GetSnippetOutputBoundary getSnippetOutputBoundary) {
        this.snippetRepository = snippetRepository;
        this.getSnippetOutputBoundary = getSnippetOutputBoundary;
    }

    public void execute(String snippetName, String folderName) {
        CodeSnippet snippet = snippetRepository.getSnippet(snippetName, folderName);
        GetSnippetOutputData getSnippetOutputData = new GetSnippetOutputData(snippet.getName(), snippet.getCode(), snippet.getDescription(), snippet.getLanguage());
        getSnippetOutputBoundary.loadSnippet(getSnippetOutputData);
    }
}
