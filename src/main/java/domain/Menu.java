package domain;

import java.util.List;

public class Menu {
    List<Folder> folders;

    public Menu(List<Folder> folders) {
        this.folders = folders;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public void add_folder(Folder newFolder) {
        this.folders.add(newFolder);
    }
}
