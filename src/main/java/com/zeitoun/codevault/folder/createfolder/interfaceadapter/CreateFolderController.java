package com.zeitoun.codevault.folder.createfolder.interfaceadapter;

import com.zeitoun.codevault.folder.createfolder.usecase.CreateFolderInteractor;

public class CreateFolderController {
    private final CreateFolderInteractor createFolderInteractor;

    public CreateFolderController(CreateFolderInteractor createFolderInteractor) {
        this.createFolderInteractor = createFolderInteractor;
    }

    public void execute(String folder) {
        createFolderInteractor.execute(folder);
    }
}
