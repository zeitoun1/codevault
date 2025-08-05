package com.zeitoun.codevault.folder.renamefolder.interfaceadapter;

import com.zeitoun.codevault.folder.renamefolder.usecase.RenameFolderOutputBoundary;
import com.zeitoun.codevault.folder.view.FoldersPaneViewModel;

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
