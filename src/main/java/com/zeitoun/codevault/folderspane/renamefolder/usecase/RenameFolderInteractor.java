package com.zeitoun.codevault.folderspane.renamefolder.usecase;

import com.zeitoun.codevault.database.repostiories.FoldersRepository;

public class RenameFolderInteractor {
    private final RenameFolderOutputBoundary renameFolderOutputBoundary;
    private final FoldersRepository foldersRepository;


    public RenameFolderInteractor(RenameFolderOutputBoundary renameFolderOutputBoundary, FoldersRepository foldersRepository) {
        this.renameFolderOutputBoundary = renameFolderOutputBoundary;
        this.foldersRepository = foldersRepository;
    }

    public void execute(int index, String oldFolderName, String newFolderName) {

        if (oldFolderName.equals(newFolderName)) {
            return ;
        }
        else if (foldersRepository.isMember(newFolderName)) {
            renameFolderOutputBoundary.showErrorMessage("folder already exists");
        } else {
            foldersRepository.renameFolder(oldFolderName, newFolderName);
            renameFolderOutputBoundary.setFolder(index, newFolderName);
        }
    }
}
