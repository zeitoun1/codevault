package com.zeitoun.codevault.shared;

/**
 * A class which allows different views to have access to shared information like the current folder,
 * this is done by injecting the AppContext into the appropriate interactors to set the fields in the AppContext,
 * or into the appropriate controllers to supply them with certain shared information, that is only found in another
 * view.
 */
public class AppContext {
    private String selectedFolder;

    public String getSelectedSnippet() {
        return selectedSnippet;
    }

    public void setSelectedSnippet(String selectedSnippet) {
        this.selectedSnippet = selectedSnippet;
    }

    private String selectedSnippet;

    public void setSelectedFolder(String selectedFolder) {
        this.selectedFolder = selectedFolder;
    }

    public String getSelectedFolder() {
        return selectedFolder;
    }
}
