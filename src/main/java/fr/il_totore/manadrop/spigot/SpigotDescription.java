package fr.il_totore.manadrop.spigot;

import fr.il_totore.manadrop.Description;
import org.gradle.api.Project;

import java.io.File;
import java.util.Optional;

public class SpigotDescription extends Description {

    private Optional<Load> load = Optional.empty();

    public SpigotDescription(Project project) {
        super(project, new File(project.getBuildDir(), "resources/main/plugin.yml"));
    }

    public void load(Load load) {
        this.load = Optional.ofNullable(load);
    }

    public void load(String load) {
        load(Load.valueOf(load.toUpperCase()));
    }

    public enum Load {
        STARTUP, POSTWORLD
    }
}
