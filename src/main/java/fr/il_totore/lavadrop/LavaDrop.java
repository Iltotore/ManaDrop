package fr.il_totore.lavadrop;

import fr.il_totore.lavadrop.bungeecord.BungeePlugin;
import fr.il_totore.lavadrop.spigot.SpigotPlugin;
import fr.il_totore.lavadrop.task.BuildBungeecord;
import fr.il_totore.lavadrop.task.BuildSpigot;
import fr.il_totore.lavadrop.task.BuildTools;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class LavaDrop implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        BuildTools buildTools = project.getTasks().create("buildTools", BuildTools.class);
        buildTools.setGroup("spigot");
        BuildSpigot buildSpigot = project.getTasks().create("spigotPlugin", BuildSpigot.class);
        buildSpigot.setGroup("spigot");

        project.getExtensions().create("spigot", SpigotPlugin.class, project);


        BuildBungeecord buildBungeecord = project.getTasks().create("bungeePlugin", BuildBungeecord.class);
        buildBungeecord.setGroup("bungeecord");

        project.getExtensions().create("bungee", BungeePlugin.class, project);
    }
}
