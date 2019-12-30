package fr.il_totore.manadrop.paper.task;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;

public class ClonePaper extends DefaultTask {

    private String uri = "https://github.com/PaperMC/Paper";
    private String branch = "master";
    private File directory;

    @Inject
    public ClonePaper(File defaultDir) {
        this.directory = defaultDir;
    }

    @TaskAction
    public void run() throws GitAPIException {
        if(directory.exists()) {
            System.out.println("Clearing " + directory.getAbsolutePath());
            if(!directory.delete()) {
                System.err.println("Unable to delete directory !");
            }
        }
        Git.cloneRepository()
                .setURI(uri)
                .setBranch(branch)
                .setDirectory(directory)
                .call();
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

    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}
