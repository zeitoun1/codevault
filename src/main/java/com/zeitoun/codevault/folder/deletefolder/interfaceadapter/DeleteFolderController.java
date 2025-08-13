package com.zeitoun.codevault.folder.deletefolder.interfaceadapter;

import com.zeitoun.codevault.folder.deletefolder.usecase.DeleteFolderInteractor;
import com.zeitoun.codevault.shared.AppContext;

public class DeleteFolderController {
    private final AppContext appContext;
    private final DeleteFolderInteractor deleteFolderInteractor;

    public DeleteFolderController(AppContext appContext, DeleteFolderInteractor deleteFolderInteractor) {
        this.appContext = appContext;
        this.deleteFolderInteractor = deleteFolderInteractor;
    }

    public void deleteFolder(int selectedIndex) {
        deleteFolderInteractor.execute(appContext.getSelectedFolder(), selectedIndex);
    }
}
