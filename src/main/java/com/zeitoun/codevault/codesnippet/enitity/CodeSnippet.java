package com.zeitoun.codevault.codesnippet.enitity;

/**
 * A class representing the code snippet.
 */
public class CodeSnippet {
    private String code;
    private String name;
    private String description;
    private String language;

    public CodeSnippet(String code, String name, String description, String language) {
        this.code = code;
        this.name = name;
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
