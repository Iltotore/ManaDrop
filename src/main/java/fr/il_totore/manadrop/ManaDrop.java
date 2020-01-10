package fr.il_totore.manadrop;

import fr.il_totore.manadrop.bungeecord.BungeeExtension;
import fr.il_totore.manadrop.bungeecord.task.BuildBungeecord;
import fr.il_totore.manadrop.mcp.MCPExtension;
import fr.il_totore.manadrop.mcp.task.CopyClientData;
import fr.il_totore.manadrop.mcp.task.DecompileClient;
import fr.il_totore.manadrop.mcp.task.DownloadMCP;
import fr.il_totore.manadrop.mcp.task.ExtractMCP;
import fr.il_totore.manadrop.paper.PaperExtension;
import fr.il_totore.manadrop.paper.task.ClonePaper;
import fr.il_totore.manadrop.paper.task.PaperScript;
import fr.il_totore.manadrop.spigot.SpigotExtension;
import fr.il_totore.manadrop.spigot.task.BuildSpigot;
import fr.il_totore.manadrop.spigot.task.BuildTools;
import fr.il_totore.manadrop.task.CheckYaml;
import fr.il_totore.manadrop.util.MinecraftOS;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.Copy;

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

        //MCP
        MCPExtension mcpExtension = project.getExtensions().create("mcp", MCPExtension.class, project);

        DownloadMCP downloadMCP = project.getTasks().create("downloadMCP", DownloadMCP.class, downloadDir);
        downloadMCP.setGroup("mcp");

        ExtractMCP extractMCP = project.getTasks().create("extractMCP", ExtractMCP.class, downloadDir, project.getProjectDir());
        extractMCP.setGroup("mcp");

        CopyClientData copyClientData = project.getTasks().create("copyClientData", CopyClientData.class);
        copyClientData.setGroup("mcp");
        copyClientData.update(mcpExtension);

        Copy copyMinecraftClient = project.getTasks().create("copyMinecraftClient", Copy.class);
        copyMinecraftClient.setGroup("mcp");
        copyMinecraftClient.from(new File(project.getProjectDir(), "src/minecraft"));

        DecompileClient decompileClient = project.getTasks().create("decompileClient", DecompileClient.class, project.getProjectDir(), os);
        decompileClient.setShowLogs(true);
        decompileClient.setGroup("mcp");
    }


}
