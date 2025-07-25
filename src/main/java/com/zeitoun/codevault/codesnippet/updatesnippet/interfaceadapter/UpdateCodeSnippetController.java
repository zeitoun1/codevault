package com.zeitoun.codevault.codesnippet.updatesnippet.interfaceadapter;

import com.zeitoun.codevault.codesnippet.updatesnippet.usecase.UpdateCodeSnippetInputData;
import com.zeitoun.codevault.codesnippet.updatesnippet.usecase.UpdateCodeSnippetInteractor;
import com.zeitoun.codevault.shared.AppContext;

/**
 * A Controller for creating code snippets.
 *
 */
public class UpdateCodeSnippetController {
    private final UpdateCodeSnippetInteractor updateCodeSnippetInteractor;
    private final AppContext appContext;

    public UpdateCodeSnippetController(UpdateCodeSnippetInteractor updateCodeSnippetInteractor, AppContext appContext) {
        this.updateCodeSnippetInteractor = updateCodeSnippetInteractor;
        this.appContext = appContext;
    }

    public void execute(String code, String name, String description, String language) {
        final UpdateCodeSnippetInputData newSnippet = new UpdateCodeSnippetInputData(appContext.getSelectedSnippet(), appContext.getSelectedFolder(), code, name, description, language, appContext.getSelectedFolder());
        this.updateCodeSnippetInteractor.execute(newSnippet);
    }

}
