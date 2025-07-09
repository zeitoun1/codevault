package com.zeitoun.codevault.folder.createfolder.interfaceadapter;

import com.zeitoun.codevault.folder.createfolder.usecase.CreateFolderOutputBoundary;
import com.zeitoun.codevault.folder.view.FoldersPaneViewModel;

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
