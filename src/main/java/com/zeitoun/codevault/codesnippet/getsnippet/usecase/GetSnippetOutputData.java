package com.zeitoun.codevault.codesnippet.getsnippet.usecase;

public class GetSnippetOutputData {
    private String name;
    private String code;
    private String description;
    private String language;

    public GetSnippetOutputData(String name, String code, String description, String language) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.language = language;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
