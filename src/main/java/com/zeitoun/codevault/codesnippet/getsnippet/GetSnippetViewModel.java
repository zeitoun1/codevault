package com.zeitoun.codevault.codesnippet.getsnippet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GetSnippetViewModel {
    private final StringProperty name = new SimpleStringProperty();
    private String code;
    private String description;
    private String language;


    public StringProperty getNameProperty() {
        return name;
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
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
