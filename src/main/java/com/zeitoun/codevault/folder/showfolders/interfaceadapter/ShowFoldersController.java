package com.zeitoun.codevault.folder.showfolders.interfaceadapter;

import com.zeitoun.codevault.folder.showfolders.usecase.ShowFoldersInteractor;

public class ShowFoldersController {
    ShowFoldersInteractor showFoldersInteractor;

    public ShowFoldersController(ShowFoldersInteractor showFoldersInteractor) {
        this.showFoldersInteractor = showFoldersInteractor;
    }

    public void execute() {
        showFoldersInteractor.execute();
    }

}
