package com.zeitoun.codevault.folderspane.showfolders.interfaceadapter;

import com.zeitoun.codevault.folderspane.showfolders.usecase.ShowFoldersOutPutBoundary;
import com.zeitoun.codevault.folderspane.view.FoldersPaneViewModel;

import java.util.List;

public class ShowFoldersPresenter implements ShowFoldersOutPutBoundary {

    private final FoldersPaneViewModel foldersPaneViewModel;

    public ShowFoldersPresenter(FoldersPaneViewModel foldersPaneViewModel) {
        this.foldersPaneViewModel = foldersPaneViewModel;
    }


    @Override
    public void setFoldersList(List<String> foldersList) {
        foldersPaneViewModel.getFolders().setAll(foldersList);
    }
}
