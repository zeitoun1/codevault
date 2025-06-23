package com.zeitoun.codevault.codesnippet.dataaccess;

/**
 * Defines database operations involving code snippets.
 */
public interface SnippetRepository {
    void saveSnippet(String code, String name, String description, String language);
    Boolean isMember(String name, String language);

}
