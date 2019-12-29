package fr.il_totore.manadrop.task;

import fr.il_totore.manadrop.util.LoggerProcessBuilder;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;

public class ExecuteScript extends DefaultTask {

    private File file;
    private File workDir;
    private String[] commands;
    private boolean showLogs = false;

    @Inject
    public ExecuteScript(File paperFile, File workDir, String... commands) {
        this.file = paperFile;
        this.workDir = workDir;
        this.commands = commands;
    }

    @TaskAction
    public void run() {
        LoggerProcessBuilder processBuilder = new LoggerProcessBuilder(new ProcessBuilder(),
                showLogs ? System.out : null,
                showLogs ? System.err : null);


    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getWorkDir() {
        return workDir;
    }

    public void setWorkDir(File workDir) {
        this.workDir = workDir;
    }

    public String[] getCommands() {
        return commands;
    }

    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    public boolean isShowLogs() {
        return showLogs;
    }

    public void setShowLogs(boolean showLogs) {
        this.showLogs = showLogs;
    }
}
