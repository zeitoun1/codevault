package com.zeitoun.codevault.folderspane.createfolder;

import com.zeitoun.codevault.folderspane.interfaceadapter.FoldersPaneViewModel;

public class CreateFolderPresenter implements CreateFolderOutputBoundary{
    private final FoldersPaneViewModel foldersPaneViewModel;

    public CreateFolderPresenter(FoldersPaneViewModel foldersPaneViewModel) {
        this.foldersPaneViewModel = foldersPaneViewModel;
    }


    @Override
    public void updateFoldersList(String folder) {
        this.foldersPaneViewModel.getFolders().add(folder);
    }

    @Override
    public void showErrorMessage(String error) {
        foldersPaneViewModel.setError(error);
    }
}
