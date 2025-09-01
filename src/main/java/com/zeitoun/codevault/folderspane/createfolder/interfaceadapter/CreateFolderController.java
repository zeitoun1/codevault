package com.zeitoun.codevault.folderspane.createfolder.interfaceadapter;

import com.zeitoun.codevault.folderspane.createfolder.usecase.CreateFolderInteractor;

public class CreateFolderController {
    private final CreateFolderInteractor createFolderInteractor;

    public CreateFolderController(CreateFolderInteractor createFolderInteractor) {
        this.createFolderInteractor = createFolderInteractor;
    }

    public void execute(String folder) {
        createFolderInteractor.execute(folder);
    }
}
