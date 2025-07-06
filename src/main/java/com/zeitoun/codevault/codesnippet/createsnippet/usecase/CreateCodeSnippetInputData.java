package com.zeitoun.codevault.codesnippet.createsnippet.usecase;

public class CreateCodeSnippetInputData {
    private String code;
    private String name;
    private String description;
    private String language;
    private String folder;



    public CreateCodeSnippetInputData(String code, String name, String description, String language, String folder) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.language = language;
        this.folder = folder;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
