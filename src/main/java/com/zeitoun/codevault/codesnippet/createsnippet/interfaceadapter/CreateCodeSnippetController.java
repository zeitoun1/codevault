package com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter;

import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetInputData;
import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetInteractor;
import com.zeitoun.codevault.shared.AppContext;

/**
 * A Controller for creating code snippets.
 *
 */
public class CreateCodeSnippetController {
    private final CreateCodeSnippetInteractor createCodeSnippetInteractor;
    private final AppContext appContext;

    public CreateCodeSnippetController(CreateCodeSnippetInteractor createCodeSnippetInteractor, AppContext appContext) {
        this.createCodeSnippetInteractor = createCodeSnippetInteractor;
        this.appContext = appContext;
    }

    public void execute(String code, String name, String description, String language) {
        final CreateCodeSnippetInputData newSnippet = new CreateCodeSnippetInputData(code, name, description, language, appContext.getSelectedFolder());
        this.createCodeSnippetInteractor.execute(newSnippet);
    }

}
