package com.zeitoun.codevault.folderspane.createfolder;

public interface CreateFolderOutputBoundary {
    void updateFoldersList(String folder);
    void showErrorMessage(String error);
}
