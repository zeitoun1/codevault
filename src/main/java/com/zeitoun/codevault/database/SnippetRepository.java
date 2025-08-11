package com.zeitoun.codevault.database;

import com.zeitoun.codevault.codesnippet.enitity.CodeSnippet;

import java.util.List;

/**
 * Defines database operations involving code snippets.
 */
public interface SnippetRepository {
    void saveSnippet(String code, String name, String description, String language, String folder);
    Boolean isMember(String name, String folder);
    List<String> getSnippets(String selectedFolder);
    CodeSnippet getSnippet(String name, String selectedFolder);
    void updateSnippet(String selectedSnippet, String selectedFolder, String code, String description, String language);
    void renameSnippet(String oldSnippetName, String newSnippetName, String selectedFolder);

}
