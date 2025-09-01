package com.zeitoun.codevault.folderspane.deletefolder.usecase;

import com.zeitoun.codevault.database.repostiories.FoldersRepository;

public class DeleteFolderInteractor {
    private final FoldersRepository foldersRepository;
    private final DeleteFolderOutputBoundary deleteFolderOutputBoundary;

    public DeleteFolderInteractor(FoldersRepository foldersRepository, DeleteFolderOutputBoundary deleteFolderOutputBoundary) {
        this.foldersRepository = foldersRepository;
        this.deleteFolderOutputBoundary = deleteFolderOutputBoundary;
    }

    public void execute(String selectedFolder, int selectedIndex) {
        foldersRepository.deleteFolder(selectedFolder);
        deleteFolderOutputBoundary.removeFolderFromList(selectedIndex);
    }
}
