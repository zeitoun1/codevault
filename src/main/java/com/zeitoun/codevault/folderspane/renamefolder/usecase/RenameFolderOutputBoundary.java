package com.zeitoun.codevault.folderspane.renamefolder.usecase;

public interface RenameFolderOutputBoundary {
    void setFolder(int index, String newFolderName);
    void showErrorMessage(String error);
}
