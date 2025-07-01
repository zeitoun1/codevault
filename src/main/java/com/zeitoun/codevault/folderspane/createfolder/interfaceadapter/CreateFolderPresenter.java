package com.zeitoun.codevault.folderspane.createfolder.interfaceadapter;

import com.zeitoun.codevault.folderspane.createfolder.usecase.CreateFolderOutputBoundary;
import com.zeitoun.codevault.folderspane.view.FoldersPaneViewModel;

public class CreateFolderPresenter implements CreateFolderOutputBoundary {
    private final FoldersPaneViewModel foldersPaneViewModel;

    public CreateFolderPresenter(FoldersPaneViewModel foldersPaneViewModel) {
        this.foldersPaneViewModel = foldersPaneViewModel;
    }


    @Override
    public void updateFoldersList(String folder) {
        this.foldersPaneViewModel.getFolders().add(0, folder);
    }

    @Override
    public void showErrorMessage(String error) {
        foldersPaneViewModel.setError(error);
    }
}
