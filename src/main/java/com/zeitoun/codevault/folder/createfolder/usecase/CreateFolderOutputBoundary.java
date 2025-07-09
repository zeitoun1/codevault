package com.zeitoun.codevault.folder.createfolder.usecase;

public interface CreateFolderOutputBoundary {
    void updateFoldersList(String folder);
    void showErrorMessage(String error);
}
