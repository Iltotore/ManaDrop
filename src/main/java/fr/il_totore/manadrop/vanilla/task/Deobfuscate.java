package fr.il_totore.manadrop.vanilla.task;

import cuchaz.enigma.EnigmaProject;
import cuchaz.enigma.command.Command;
import cuchaz.enigma.throwables.MappingParseException;
import cuchaz.enigma.translation.mapping.EntryMapping;
import cuchaz.enigma.translation.mapping.serde.MappingFormat;
import cuchaz.enigma.translation.mapping.tree.EntryTree;
import fr.il_totore.manadrop.vanilla.Enigma;
import fr.il_totore.manadrop.vanilla.VanillaExtension;
import fr.il_totore.manadrop.vanilla.VanillaModule;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

public class Deobfuscate extends DefaultTask {


   /* @Inject
    public Deobfuscate(Enigma enigma, File downloadDir) {
        super(enigma.getWorkDir(), () -> Arrays.stream(enigma.getCommandLine())
                .map(string -> string.replace("${DOWNLOADDIR}", downloadDir.getAbsolutePath()))
                .collect(Collectors.toList()));
    }*/

    private VanillaExtension extension;

    @Inject
    public Deobfuscate(VanillaExtension extension) {
        this.extension = extension;
    }

    @TaskAction
    public void run() throws IOException, MappingParseException {
        cuchaz.enigma.Enigma enigma = cuchaz.enigma.Enigma.create();
        deobfuscate(enigma, extension.getClient(), extension.getEnigma());
        deobfuscate(enigma, extension.getServer(), extension.getEnigma());
    }

    private void deobfuscate(cuchaz.enigma.Enigma enigma, VanillaModule module, Enigma settings) throws IOException, MappingParseException {
        Command.ConsoleProgressListener listener = new Command.ConsoleProgressListener();
        File jarFile = new File(settings.getJarsDir(), module.getMappingType() + ".jar");
        File mappingFile = new File(settings.getJarsDir(), module.getMappingType() + ".txt");
        File finalDir = new File(module.getDir(), "src/main/java");
        if(!jarFile.exists() || !mappingFile.exists()) {
            System.out.println("Jar file or mapping file is missing. Aborting !");
            return;
        }

        if(!module.getDir().exists()) module.getDir().mkdirs();

        System.out.println("Opening " + module.getMappingType() + ".jar");
        EnigmaProject project = enigma.openJar(jarFile.toPath(), listener);
        System.out.println("Opening mappings" + module.getMappingType() + ".txt");
        EntryTree<EntryMapping> mappings = MappingFormat.PROGUARD.read(mappingFile.toPath(), listener, enigma.getProfile().getMappingSaveParameters());
        project.setMappings(mappings);
        System.out.println("Deobfuscating...");
        EnigmaProject.JarExport export = project.exportRemappedJar(listener);
        System.out.println("Decompiling...");
        EnigmaProject.SourceExport sources = export.decompile(listener);
        sources.write(finalDir.toPath(), listener);
        System.out.println("Successfully decompiled sources to " + module.getDir());
    }
}
