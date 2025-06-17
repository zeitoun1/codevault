package com.zeitoun.codevault.codesnippet.createsnippet;

import java.util.List;

public class CreateCodeSnippetViewModel {
    List<String> editorLanguages;

    public CreateCodeSnippetViewModel(List<String> editorLanguages) {
        this.editorLanguages = editorLanguages;
    }

    public List<String> getEditorLanguages() {
        return editorLanguages;
    }

    public void setEditorLanguages(List<String> editorLanguages) {
        this.editorLanguages = editorLanguages;
    }
}
