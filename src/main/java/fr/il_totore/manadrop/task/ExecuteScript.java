package fr.il_totore.manadrop.task;

import fr.il_totore.manadrop.util.LoggerProcessBuilder;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

public class ExecuteScript extends DefaultTask {

    private File workDir;
    private String[] commands;
    private boolean showLogs = false;

    @Inject
    public ExecuteScript(File workDir, String... commands) {
        this.workDir = workDir;
        this.commands = commands;
    }

    @TaskAction
    public void run() throws IOException, InterruptedException {
        LoggerProcessBuilder processBuilder = new LoggerProcessBuilder(new ProcessBuilder(commands),
                showLogs ? System.out : null,
                showLogs ? System.err : null);
        processBuilder.directory(workDir);
        System.out.println("Starting process...");
        System.out.println("Exit code: " + processBuilder.startAndWait().exitValue());
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
