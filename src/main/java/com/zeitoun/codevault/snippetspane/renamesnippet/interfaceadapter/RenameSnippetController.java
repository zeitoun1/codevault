package com.zeitoun.codevault.snippetspane.renamesnippet.interfaceadapter;

import com.zeitoun.codevault.shared.AppContext;
import com.zeitoun.codevault.snippetspane.renamesnippet.usecase.RenameSnippetInteractor;

public class RenameSnippetController {
    private final AppContext appContext;
    private final RenameSnippetInteractor renameSnippetInteractor;

    public RenameSnippetController(RenameSnippetInteractor renameSnippetInteractor, AppContext appContext) {
        this.appContext = appContext;
        this.renameSnippetInteractor = renameSnippetInteractor;
    }

    public void renameSnippet(int index, String newSnippetName) {
        renameSnippetInteractor.execute(index, appContext.getSelectedSnippet(), newSnippetName, appContext.getSelectedFolder());
    }


}
