package fr.il_totore.manadrop.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ExtractFile extends DefaultTask {

    private File from;
    private File to;

    public ExtractFile() {

    }

    public ExtractFile(File from, File to) {
        this.from = from;
        this.to = to;
    }

    @TaskAction
    public void run() throws IOException {
        Objects.requireNonNull(from, "Archive file cannot be null !");
        Objects.requireNonNull(to, "Destination file cannot be null !");
        System.out.println("Extracting " + from.getName() + "...");
        ZipUtil.unpack(from, to);
    }

    public File getFrom() {
        return from;
    }

    public void setFrom(File from) {
        this.from = from;
    }

    public File getTo() {
        return to;
    }

    public void setTo(File to) {
        this.to = to;
    }
}
