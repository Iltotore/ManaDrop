package fr.il_totore.manadrop.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.simpleyaml.configuration.file.YamlConfiguration;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckYaml extends DefaultTask {

    private List<File> files = new ArrayList<>();
    private boolean stopOnError = true;

    @TaskAction
    public void run() {
        for(File file : files) {
            if(!file.exists()) {
                System.err.println("Skipping non-existing file: " + file.getPath());
                continue;
            }
            try {
                new YamlConfiguration().load(file);
            } catch(InvalidConfigurationException | IOException e) {
                if(stopOnError) throw new RuntimeException(e);
                e.printStackTrace();
                System.err.println("Invalid YAML file: " + file.getPath());
            }
        }
    }

    public void check(File... files) {
        this.files.addAll(Arrays.asList(files));
    }

    public void stopOnError(boolean b) {
        stopOnError = b;
    }
}
