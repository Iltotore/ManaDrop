package fr.il_totore.manadrop.vanilla;

import groovy.lang.Closure;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.util.ConfigureUtil;

import javax.inject.Inject;
import java.io.File;

public class VanillaExtension {

    private Enigma enigma;
    private VanillaModule client;
    private VanillaModule server;
    private File mappingsDir;

    @Inject
    public VanillaExtension(Project project) {
        this.enigma = new Enigma(project);
        this.mappingsDir = project.file("mappings/");
        this.client = new VanillaModule(project, "client", "Client");
        this.server = new VanillaModule(project, "server", "Server");
    }

    public Enigma getEnigma() {
        return enigma;
    }

    public void setEnigma(Action<Enigma> action) {
        action.execute(enigma);
    }

    public VanillaModule getClient() {
        return client;
    }

    public void setClient(Action<VanillaModule> action) {
        action.execute(this.client);
    }

    public void setClient(Closure<VanillaModule> closure) {
        ConfigureUtil.configure(closure, this.client);
    }


    public VanillaModule getServer() {
        return server;
    }

    public void setServer(Action<VanillaModule> action) {
        action.execute(this.server);
    }

    public File getMappingsDir() {
        return mappingsDir;
    }

    public void setMappingsDir(File mappingsDir) {
        this.mappingsDir = mappingsDir;
    }
}
