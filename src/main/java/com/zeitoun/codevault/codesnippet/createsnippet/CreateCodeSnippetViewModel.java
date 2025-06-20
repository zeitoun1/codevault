package com.zeitoun.codevault.codesnippet.createsnippet;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class CreateCodeSnippetViewModel {
    List<String> editorLanguages;
    StringProperty errorMessage;
    StringProperty ConfirmationMessage;


    public CreateCodeSnippetViewModel(List<String> editorLanguages) {
        this.editorLanguages = editorLanguages;
        this.errorMessage = new SimpleStringProperty();
        this.ConfirmationMessage = new SimpleStringProperty();
    }

    public List<String> getEditorLanguages() {
        return editorLanguages;
    }

    public void setEditorLanguages(List<String> editorLanguages) {
        this.editorLanguages = editorLanguages;
    }
}
