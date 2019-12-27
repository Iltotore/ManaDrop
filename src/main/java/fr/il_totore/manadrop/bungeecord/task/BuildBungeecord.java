package fr.il_totore.manadrop.bungeecord.task;

import fr.il_totore.manadrop.bungeecord.BungeeExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class BuildBungeecord extends DefaultTask {

    @TaskAction
    public void run() {
        BungeeExtension plugin = (BungeeExtension) getProject().getExtensions().getByName("bungeecord");
        plugin.getDescription().ifPresent(desc -> desc.run(this));
    }
}
