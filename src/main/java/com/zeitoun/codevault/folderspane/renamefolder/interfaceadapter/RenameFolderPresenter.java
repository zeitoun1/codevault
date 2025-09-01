package com.zeitoun.codevault.folderspane.renamefolder.interfaceadapter;

import com.zeitoun.codevault.folderspane.renamefolder.usecase.RenameFolderOutputBoundary;
import com.zeitoun.codevault.folderspane.view.FoldersPaneViewModel;

public class RenameFolderPresenter implements RenameFolderOutputBoundary {
    private final FoldersPaneViewModel foldersPaneViewModel;

    public RenameFolderPresenter(FoldersPaneViewModel foldersPaneViewModel) {
        this.foldersPaneViewModel = foldersPaneViewModel;
    }

    @Override
    public void setFolder(int index, String newFolderName) {
        foldersPaneViewModel.getFolders().set(index, newFolderName);
    }

    @Override
    public void showErrorMessage(String error) {
        foldersPaneViewModel.setError(error);
    }
}
