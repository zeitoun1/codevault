package com.zeitoun.codevault.codesnippet.createsnippet;

/**
 * Output Boundary for the CreateCodeSnippet use case.
 */
public interface CreateCodeSnippetOutputBoundary {
    void SwitchToHomeView();
    void showErrorMessage(String error);
    void showReplaceConfirmation(String message);
}
