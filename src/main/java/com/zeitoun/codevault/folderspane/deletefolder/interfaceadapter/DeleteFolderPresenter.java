package com.zeitoun.codevault.folderspane.deletefolder.interfaceadapter;

import com.zeitoun.codevault.folderspane.deletefolder.usecase.DeleteFolderOutputBoundary;
import com.zeitoun.codevault.folderspane.view.FoldersPaneViewModel;

public class DeleteFolderPresenter implements DeleteFolderOutputBoundary {
    private final FoldersPaneViewModel foldersPaneViewModel;

    public DeleteFolderPresenter(FoldersPaneViewModel foldersPaneViewModel) {
        this.foldersPaneViewModel = foldersPaneViewModel;
    }

    @Override
    public void removeFolderFromList(int selectedIndex) {
        foldersPaneViewModel.getFolders().remove(selectedIndex);
    }
}
