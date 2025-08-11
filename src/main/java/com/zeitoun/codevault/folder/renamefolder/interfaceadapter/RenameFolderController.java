package com.zeitoun.codevault.folder.renamefolder.interfaceadapter;

import com.zeitoun.codevault.folder.renamefolder.usecase.RenameFolderInteractor;
import com.zeitoun.codevault.shared.AppContext;

public class RenameFolderController {
    private final RenameFolderInteractor renameFolderInteractor;
    private final AppContext appContext;

    public RenameFolderController(RenameFolderInteractor renameFolderInteractor, AppContext appContext) {
        this.renameFolderInteractor = renameFolderInteractor;
        this.appContext = appContext;
    }

    public void renameFolder(int index, String newFolderName) {
        renameFolderInteractor.execute(index, appContext.getSelectedFolder(), newFolderName);
    }
}
