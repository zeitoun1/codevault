package com.zeitoun.codevault.folderspane.createfolder;

public class CreateFolderController {
    private final CreateFolderInteractor createFolderInteractor;

    public CreateFolderController(CreateFolderInteractor createFolderInteractor) {
        this.createFolderInteractor = createFolderInteractor;
    }

    public void execute(String folder) {
        createFolderInteractor.execute(folder);
    }
}
