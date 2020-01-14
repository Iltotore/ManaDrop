package fr.il_totore.manadrop.vanilla;

import org.gradle.api.Project;

import java.io.File;

public class Enigma {

    private File jarsDir;
    private File workDir;

    public Enigma(Project project) {
        this.jarsDir = project.file("jarsDir");
        this.workDir = project.file("enigmaWork");
    }

    public File getJarsDir() {
        return jarsDir;
    }

    public File getWorkDir() {
        return workDir;
    }

    public void setWorkDir(File workDir) {
        this.workDir = workDir;
    }
}
