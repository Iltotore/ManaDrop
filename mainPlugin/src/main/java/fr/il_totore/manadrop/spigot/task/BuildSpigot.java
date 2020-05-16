package fr.il_totore.manadrop.spigot.task;

import fr.il_totore.manadrop.spigot.SpigotExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class BuildSpigot extends DefaultTask {

    @TaskAction
    public void run() {
        SpigotExtension plugin = (SpigotExtension) getProject().getExtensions().getByName("spigot");
        plugin.getDescription().ifPresent(desc -> desc.run(this));
    }
}
