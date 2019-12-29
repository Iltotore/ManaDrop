package fr.il_totore.manadrop.paper;

import java.io.File;

public class PaperExtension {

    private File paperFile;
    private File paperDir;

    public File getPaperFile() {
        return paperFile;
    }

    public File getPaperDir() {
        return paperDir;
    }

    public void setPaperFile(File paperFile) {
        this.paperFile = paperFile;
    }

    public void setPaperDir(File paperDir) {
        this.paperDir = paperDir;
    }
}
