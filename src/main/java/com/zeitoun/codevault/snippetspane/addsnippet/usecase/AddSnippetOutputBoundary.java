package com.zeitoun.codevault.snippetspane.addsnippet.usecase;

public interface AddSnippetOutputBoundary {
    void addToSnippetList(String snippetName);
    void showErrorMessage(String errorMessage);
}
