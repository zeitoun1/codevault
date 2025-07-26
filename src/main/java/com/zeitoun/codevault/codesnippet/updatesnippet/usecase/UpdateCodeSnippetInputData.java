package com.zeitoun.codevault.codesnippet.updatesnippet.usecase;

public class UpdateCodeSnippetInputData {
    public String getSelectedSnippet() {
        return selectedSnippet;
    }

    public String getSelectedFolder() {
        return selectedFolder;
    }

    private final String selectedSnippet;
    private final String selectedFolder;
    private final String code;
    private final String description;
    private final String language;
    private String folder;



    public UpdateCodeSnippetInputData(String selectedSnippet, String selectedFolder, String code, String description, String language, String folder) {
        this.selectedSnippet = selectedSnippet;
        this.selectedFolder = selectedFolder;
        this.code = code;
        this.description = description;
        this.language = language;
        this.folder = folder;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
