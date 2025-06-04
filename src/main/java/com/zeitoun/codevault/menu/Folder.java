package com.zeitoun.codevault.menu;
import com.zeitoun.codevault.codesnippet.enitity.CodeSnippet;

import java.util.List;

public class Folder {
    String name;
    List<CodeSnippet> snippets;

    public Folder(List<CodeSnippet> snippets, String name) {
        this.snippets = snippets;
        this.name = name;
    }


    public List<CodeSnippet> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<CodeSnippet> snippets) {
        this.snippets = snippets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSnippet(CodeSnippet newSnippet) {
        this.snippets.add(newSnippet);
    }
}
