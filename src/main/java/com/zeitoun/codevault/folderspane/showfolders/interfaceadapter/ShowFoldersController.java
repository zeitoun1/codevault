package com.zeitoun.codevault.folderspane.showfolders.interfaceadapter;

import com.zeitoun.codevault.folderspane.showfolders.usecase.ShowFoldersInteractor;

public class ShowFoldersController {
    ShowFoldersInteractor showFoldersInteractor;

    public ShowFoldersController(ShowFoldersInteractor showFoldersInteractor) {
        this.showFoldersInteractor = showFoldersInteractor;
    }

    public void execute() {
        showFoldersInteractor.execute();
    }

}
