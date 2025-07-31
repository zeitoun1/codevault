package com.zeitoun.codevault.snippetspane.addSnippet;

public interface AddSnippetOutputBoundary {
    void addToSnippetList(String snippetName);
    void showErrorMessage(String errorMessage);
}
