package com.zeitoun.codevault.folderspane.createfolder.usecase;

import com.zeitoun.codevault.folderspane.dataaccess.FoldersRepository;

public class CreateFolderInteractor {
    private final FoldersRepository foldersRepository;
    private final CreateFolderOutputBoundary createFolderOutputBoundary;

    public CreateFolderInteractor(FoldersRepository foldersRepository, CreateFolderOutputBoundary createFolderOutputBoundary) {
        this.foldersRepository = foldersRepository;
        this.createFolderOutputBoundary = createFolderOutputBoundary;
    }

    public void execute(String folder) {
        if (folder.isEmpty()) {
           createFolderOutputBoundary.showErrorMessage("Folder name is missing.");
        } else if (foldersRepository.isMember(folder)) {
            createFolderOutputBoundary.showErrorMessage("A folder with that name already exists.");
        } else {
            foldersRepository.addFolder(folder);
            createFolderOutputBoundary.updateFoldersList(folder);
        }
    }
}
