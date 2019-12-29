package fr.il_totore.manadrop.mcp.task;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;

public class ClonePaper extends DefaultTask {

    private String uri = "https://github.com/PaperMC/Paper";
    private String branch = "master";
    private File tempDir;
    private File directory;

    @Inject
    public ClonePaper(File tempDir, File defaultDir) {
        this.tempDir = tempDir;
        this.directory = defaultDir;
    }

    @TaskAction
    public void run() throws GitAPIException {
        System.out.println("Cloning repository...");
        Git.cloneRepository()
                .setURI(uri)
                .setBranch(branch)
                .setDirectory(tempDir)
                .call();

        System.out.println("Copying to destination directory...");
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setBranchVersion(String version) {
        this.branch = "ver/" + version;
    }

    public File getTempDir() {
        return tempDir;
    }

    public void setTempDir(File tempDir) {
        this.tempDir = tempDir;
    }

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}
