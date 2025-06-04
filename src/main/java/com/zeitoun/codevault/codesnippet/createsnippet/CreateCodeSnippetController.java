package com.zeitoun.codevault.codesnippet.createsnippet;

public class CreateCodeSnippetController {
    private final CreateCodeSnippetInteractor createCodeSnippetInteractor;

    public CreateCodeSnippetController(CreateCodeSnippetInteractor createCodeSnippetInteractor) {
        this.createCodeSnippetInteractor = createCodeSnippetInteractor;
    }

    public void execute(String code, String name, String description, String language) {
        final CreateCodeSnippetInputData newSnippet = new CreateCodeSnippetInputData(code, name, description, language);
        this.createCodeSnippetInteractor.execute(newSnippet);
    }
}
