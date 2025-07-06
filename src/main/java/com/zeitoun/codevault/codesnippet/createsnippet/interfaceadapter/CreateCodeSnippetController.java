package com.zeitoun.codevault.codesnippet.createsnippet.interfaceadapter;

import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetInputData;
import com.zeitoun.codevault.codesnippet.createsnippet.usecase.CreateCodeSnippetInteractor;

/**
 * A Controller for creating code snippets.
 *
 */
public class CreateCodeSnippetController {
    private final CreateCodeSnippetInteractor createCodeSnippetInteractor;
    private String folder;

    public CreateCodeSnippetController(CreateCodeSnippetInteractor createCodeSnippetInteractor) {
        this.createCodeSnippetInteractor = createCodeSnippetInteractor;
    }

    public void execute(String code, String name, String description, String language) {
        final CreateCodeSnippetInputData newSnippet = new CreateCodeSnippetInputData(code, name, description, language, folder);
        this.createCodeSnippetInteractor.execute(newSnippet);
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }
}
