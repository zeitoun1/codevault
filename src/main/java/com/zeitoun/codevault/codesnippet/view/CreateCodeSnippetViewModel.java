package com.zeitoun.codevault.codesnippet.view;

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

    public ObservableList<String> getEditorLanguages() {
        return editorLanguages;
    }

    public void setEditorLanguages(List<String> editorLanguages) {
        this.editorLanguages.setAll(editorLanguages);
    }

    public String getErrorMessage() {
        return errorMessage.get();
    }

    public StringProperty errorMessageProperty() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage.get();
    }

    public StringProperty successMessageProperty() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage.setValue(successMessage);
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage.setValue(errorMessage);
    }
}
