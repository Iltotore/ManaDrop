package fr.il_totore.manadrop.spigot;

import fr.il_totore.manadrop.Description;
import org.gradle.api.Project;
import org.simpleyaml.configuration.ConfigurationSection;

import java.io.File;
import java.util.Optional;

public class SpigotDescription extends Description {

    private Optional<Load> load = Optional.empty();
    private Optional<String> apiVersion = Optional.empty();

    public SpigotDescription(Project project) {
        super(project, new File(project.getBuildDir(), "resources/main/plugin.yml"));
    }

    @Override
    public void write(ConfigurationSection section) {
        super.write(section);
        load.ifPresent(value -> section.set("load", value.name()));
        apiVersion.ifPresent(value -> section.set("api-version", value));
    }

    public void load(Load load) {
        this.load = Optional.ofNullable(load);
    }

    public void load(String load) {
        load(Load.valueOf(load.toUpperCase()));
    }

    public void apiVersion(String apiVersion) {
        this.apiVersion = Optional.ofNullable(apiVersion);
    }

    public enum Load {
        STARTUP, POSTWORLD
    }
}
