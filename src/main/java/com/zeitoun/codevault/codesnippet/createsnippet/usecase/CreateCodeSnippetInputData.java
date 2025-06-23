package com.zeitoun.codevault.codesnippet.createsnippet.usecase;

public class CreateCodeSnippetInputData {
    private String code;
    private String name;
    private String description;
    private String language;

    public CreateCodeSnippetInputData(String code, String name, String description, String language) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.language = language;
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
}
