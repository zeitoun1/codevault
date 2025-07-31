package com.zeitoun.codevault.codesnippet.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.List;

public class UpdateCodeSnippetViewModel {
    ObservableList<String> editorLanguages;
    StringProperty successMessage;
    StringProperty errorMessage;


    public UpdateCodeSnippetViewModel(ObservableList<String> editorLanguages) {
        this.editorLanguages = editorLanguages;
        this.errorMessage = new SimpleStringProperty();
        this.successMessage = new SimpleStringProperty();
    }

    public ObservableList<String> getEditorLanguages() {
        return editorLanguages;
    }

    public void setEditorLanguages(List<String> editorLanguages) {
        this.editorLanguages.setAll(editorLanguages);
    }

    public StringProperty errorMessageProperty() {
        return errorMessage;
    }

    public StringProperty successMessageProperty() {
        return successMessage;
    }


}
