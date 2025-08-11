package com.zeitoun.codevault.snippetspane.renamesnippet.usecase;

public interface RenameSnippetOutputBoundary {
    void setSnippet(int index, String newFolderName);
    void showErrorMessage(String error);
}
