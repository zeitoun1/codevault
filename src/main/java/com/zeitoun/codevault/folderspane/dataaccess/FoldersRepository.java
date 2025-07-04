package com.zeitoun.codevault.folderspane.dataaccess;

import java.util.List;

public interface FoldersRepository {
    void addFolder(String name);
    Boolean isMember(String name);
    List<String> getFolders();
}
