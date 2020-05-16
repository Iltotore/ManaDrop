package fr.il_totore.manadrop;

import fr.il_totore.manadrop.bungeecord.BungeeExtension;
import fr.il_totore.manadrop.bungeecord.task.BuildBungeecord;
import fr.il_totore.manadrop.spigot.SpigotExtension;
import fr.il_totore.manadrop.spigot.task.BuildSpigot;
import fr.il_totore.manadrop.spigot.task.BuildTools;
import fr.il_totore.manadrop.task.CheckYaml;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class ManaDrop implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        //Misc
        project.getTasks().create("checkYaml", CheckYaml.class);

        //Spigot
        BuildTools buildTools = project.getTasks().create("buildTools", BuildTools.class);
        buildTools.setGroup("spigot");
        BuildSpigot buildSpigot = project.getTasks().create("spigotPlugin", BuildSpigot.class);
        buildSpigot.setGroup("spigot");

        project.getExtensions().create("spigot", SpigotExtension.class, project);

        //Bungeecord
        BuildBungeecord buildBungeecord = project.getTasks().create("bungeePlugin", BuildBungeecord.class);
        buildBungeecord.setGroup("bungeecord");

        project.getExtensions().create("bungee", BungeeExtension.class, project);

        MinecraftRepositoryHelper.setInstance(new MinecraftRepositoryHelper.MinecraftRepository(project.getRepositories()));
    }


}
