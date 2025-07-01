package com.zeitoun.codevault.folderspane.createfolder.usecase;

public interface CreateFolderOutputBoundary {
    void updateFoldersList(String folder);
    void showErrorMessage(String error);
}
