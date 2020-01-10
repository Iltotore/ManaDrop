package fr.il_totore.manadrop.vanilla;

import org.gradle.api.Project;

import java.io.File;

public class Enigma {

    private String version = "latest";
    private String[] commandLine = {"java", "-cp", "-jar", "enigma.jar", "cuchaz.enigma.CommandMain"};
    private File workDir;

    public Enigma(Project project) {
        this.workDir = project.file("enigmaWork");
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String[] getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(String[] commandLine) {
        this.commandLine = commandLine;
    }

    public File getWorkDir() {
        return workDir;
    }

    public void setWorkDir(File workDir) {
        this.workDir = workDir;
    }
}
