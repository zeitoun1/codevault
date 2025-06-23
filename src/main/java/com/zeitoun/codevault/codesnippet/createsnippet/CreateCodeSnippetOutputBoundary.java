package com.zeitoun.codevault.codesnippet.createsnippet;

/**
 * Output Boundary for the CreateCodeSnippet use case.
 */
public interface CreateCodeSnippetOutputBoundary {
    void showSuccessMessage(String success);
    void showErrorMessage(String error);
}
