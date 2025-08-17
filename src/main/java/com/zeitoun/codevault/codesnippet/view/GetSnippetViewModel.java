package com.zeitoun.codevault.codesnippet.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GetSnippetViewModel {
    private final StringProperty name = new SimpleStringProperty();

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    private String code;
    private String description;
    private String language;


    public StringProperty nameProperty() {
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

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }
}
