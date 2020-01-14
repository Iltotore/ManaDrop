package fr.il_totore.manadrop;

import fr.il_totore.manadrop.bungeecord.BungeeExtension;
import fr.il_totore.manadrop.bungeecord.task.BuildBungeecord;
import fr.il_totore.manadrop.paper.PaperExtension;
import fr.il_totore.manadrop.paper.task.ClonePaper;
import fr.il_totore.manadrop.paper.task.PaperScript;
import fr.il_totore.manadrop.spigot.SpigotExtension;
import fr.il_totore.manadrop.spigot.task.BuildSpigot;
import fr.il_totore.manadrop.spigot.task.BuildTools;
import fr.il_totore.manadrop.task.CheckYaml;
import fr.il_totore.manadrop.util.MinecraftOS;
import fr.il_totore.manadrop.vanilla.VanillaExtension;
import fr.il_totore.manadrop.vanilla.task.Deobfuscate;
import fr.il_totore.manadrop.vanilla.task.DownloadMappings;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.File;

public class ManaDrop implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        File downloadDir = new File(project.getProjectDir(), "downloads/");
        MinecraftOS os = MinecraftOS.getByName(System.getProperty("os.name"));
        //Misc
        project.getTasks().create("checkYaml", CheckYaml.class);

        //Spigot
        BuildTools buildTools = project.getTasks().create("buildTools", BuildTools.class);
        buildTools.setGroup("spigot");
        BuildSpigot buildSpigot = project.getTasks().create("spigotPlugin", BuildSpigot.class);
        buildSpigot.setGroup("spigot");

        project.getExtensions().create("spigot", SpigotExtension.class, project);

        //Paper
        PaperExtension paperExtension = project.getExtensions().create("paper", PaperExtension.class);
        paperExtension.setPaperDir(new File(project.getProjectDir(), "eclipse/Server/"));

        ClonePaper clonePaper = project.getTasks().create("clonePaper", ClonePaper.class, new File(project.getProjectDir(), "eclipse/Server/"));
        clonePaper.setGroup("paper");

        PaperScript paperJar = project.getTasks().create("paperJar", PaperScript.class, paperExtension, new String[]{"sh", "paper", "jar"});
        paperJar.setGroup("paper");

        PaperScript paperDev = project.getTasks().create("paperDev", PaperScript.class, paperExtension, new String[]{"sh", "paper", "mcdev"});
        paperDev.setGroup("paper");

        //Bungeecord
        BuildBungeecord buildBungeecord = project.getTasks().create("bungeePlugin", BuildBungeecord.class);
        buildBungeecord.setGroup("bungeecord");

        project.getExtensions().create("bungee", BungeeExtension.class, project);

        MinecraftRepositoryHelper.setInstance(new MinecraftRepositoryHelper.MinecraftRepository(project.getRepositories()));

        //Vanilla
        VanillaExtension vanillaExtension = project.getExtensions().create("vanilla", VanillaExtension.class, project);

        DownloadMappings downloadMappings = project.getTasks().create("downloadMappings", DownloadMappings.class, vanillaExtension);
        downloadMappings.setGroup("vanilla");

        Deobfuscate deobfuscate = project.getTasks().create("deobfuscate", Deobfuscate.class, vanillaExtension);
        deobfuscate.setGroup("vanilla");
    }


}
