package com.zeitoun.codevault.folderspane.createfolder;

import com.zeitoun.codevault.folderspane.dataaccess.FoldersRepository;

public class CreateFolderInteractor {
    private final FoldersRepository foldersRepository;
    private final CreateFolderPresenter createFolderPresenter;

    public CreateFolderInteractor(FoldersRepository foldersRepository, CreateFolderPresenter createFolderPresenter) {
        this.foldersRepository = foldersRepository;
        this.createFolderPresenter = createFolderPresenter;
    }

    public void execute(String folder) {
        if (folder.isEmpty()) {
           createFolderPresenter.showErrorMessage("Folder name is missing.");
        } else if (foldersRepository.isMember(folder)) {
            createFolderPresenter.showErrorMessage("A folder with that name already exists.");
        } else {
            foldersRepository.addFolder(folder);
            createFolderPresenter.updateFoldersList(folder);
        }
    }
}
