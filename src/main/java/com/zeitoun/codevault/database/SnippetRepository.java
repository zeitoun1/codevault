package com.zeitoun.codevault.database;

import com.zeitoun.codevault.codesnippet.enitity.CodeSnippet;

import java.util.List;

/**
 * Defines database operations involving code snippets.
 */
public interface SnippetRepository {
    void saveSnippet(String code, String name, String description, String language, String folder);
    Boolean isMember(String name, String language);
    List<String> getSnippets(String selectedFolder);
    CodeSnippet getSnippet(String snippetName, String folderName);
}
