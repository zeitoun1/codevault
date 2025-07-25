package com.zeitoun.codevault.codesnippet.updatesnippet.usecase;

/**
 * Output Boundary for the CreateCodeSnippet use case.
 */
public interface UpdateCodeSnippetOutputBoundary {
    void showSuccessMessage(String success);
    void showErrorMessage(String error);
}
