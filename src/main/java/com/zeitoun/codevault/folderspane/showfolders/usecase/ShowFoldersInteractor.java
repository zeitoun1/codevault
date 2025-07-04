package com.zeitoun.codevault.folderspane.showfolders.usecase;

import com.zeitoun.codevault.folderspane.dataaccess.FoldersRepository;

public class ShowFoldersInteractor {
    FoldersRepository foldersRepository;
    ShowFoldersOutPutBoundary showFoldersOutPutBoundary;


    public ShowFoldersInteractor(FoldersRepository foldersRepository, ShowFoldersOutPutBoundary showFoldersOutPutBoundary) {
        this.showFoldersOutPutBoundary = showFoldersOutPutBoundary;
        this.foldersRepository = foldersRepository;
    }

    public void execute() {
        showFoldersOutPutBoundary.setFoldersList(foldersRepository.getFolders());
    }
}
