package com.zeitoun.codevault.folderspane.dataaccess;

public interface FoldersRepository {
    void addFolder(String name);
    Boolean isMember(String name);

}
