package com.zeitoun.codevault.codesnippet.createsnippet;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.List;

public class CreateCodeSnippetViewModel {
    ObservableList<String> editorLanguages;
    StringProperty successMessage;
    StringProperty errorMessage;


    public CreateCodeSnippetViewModel(ObservableList<String> editorLanguages) {
        this.editorLanguages = editorLanguages;
        this.errorMessage = new SimpleStringProperty();
        this.successMessage = new SimpleStringProperty();
    }

    public List<String> getEditorLanguages() {
        return editorLanguages;
    }

    public void setEditorLanguages(List<String> editorLanguages) {
        this.editorLanguages.setAll(editorLanguages);
    }
}
