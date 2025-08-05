package com.zeitoun.codevault.database;

import java.util.List;

public interface FoldersRepository {
    void addFolder(String name);
    Boolean isMember(String name);
    List<String> getFolders();
    void renameFolder(String oldName, String newName);
}
