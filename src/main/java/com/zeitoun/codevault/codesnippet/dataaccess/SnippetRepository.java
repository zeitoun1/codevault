package com.zeitoun.codevault.codesnippet.dataaccess;

/**
 * Defines database operations involving code snippets.
 */
public interface SnippetRepository {
    String saveSnippet(String code, String name, String description, String language);

}
