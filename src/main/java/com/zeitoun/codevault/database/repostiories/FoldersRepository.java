package com.zeitoun.codevault.database.repostiories;

import java.util.List;

public interface FoldersRepository {
    void addFolder(String name);
    Boolean isMember(String name);
    List<String> getFolders();
    void renameFolder(String oldName, String newName);
    void deleteFolder(String selectedFolder);
}
